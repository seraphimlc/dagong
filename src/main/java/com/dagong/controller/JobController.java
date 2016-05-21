
package com.dagong.controller;

import com.alibaba.fastjson.JSON;
import com.dagong.job.vo.JobVO;
import com.dagong.service.ApplyService;
import com.dagong.service.JobService;
import com.dagong.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/job/")
public class JobController {
    @Resource
    private JobService jobService;
    @Resource
    private ApplyService applyService;
    @Resource
    private UserService userService;


    @RequestMapping("recommand.do")
    @ResponseBody
    public String listUserJob(@CookieValue("userId") String userId, Map<String, Object> model) {
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        List<JobVO> recommendFromUser = jobService.searchJob(userId);
        if (recommendFromUser != null && !recommendFromUser.isEmpty()) {
            return JSON.toJSON(recommendFromUser).toString();
        }
        return null;
    }


    @RequestMapping("detail.do")
    @ResponseBody
    public String detail(@RequestParam("jobId") String jobId, Map<String, Object> model) {
        JobVO job = jobService.getJob(jobId);
        if (job != null) {
            return JSON.toJSON(job).toString();
        }
        return null;
    }


}

