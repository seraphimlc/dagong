
package com.dagong.controller;

import com.dagong.job.vo.JobVO;
import com.dagong.service.ApplyService;
import com.dagong.service.JobService;
import com.dagong.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/job/")
public class JobController {
    @Resource
    private JobService jobService;
    @Resource
    private ApplyService applyService;
    @Resource
    private UserService userService;


    @RequestMapping("recommend.do")
//    @ResponseBody
    public ModelAndView listUserJob(@CookieValue("userId") String userId ) {
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        List<JobVO> recommendFromUser = jobService.searchJob(userId);
        if (recommendFromUser != null && !recommendFromUser.isEmpty()) {

            return new ModelAndView("view/recommand","jobList",recommendFromUser);

        }

        return new ModelAndView("view/recommand");
    }


    @RequestMapping("detail.do")
    @ResponseBody
    public ModelAndView detail(@RequestParam("jobId") String jobId) {
        JobVO job = jobService.getJob(jobId);
        if (job != null) {
            return new ModelAndView("view/jobDetail","job",job);
        }
        return new ModelAndView("view/jobDetail");
    }


}

