package com.dagong.controller;

import com.dagong.service.ApplyService;
import com.dagong.service.JobService;
import com.dagong.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by liuchang on 16/1/28.
 */
@Controller
@RequestMapping("/job/")
public class JobController {
    @Resource
    private JobService jobService;
    @Resource
    private ApplyService applyService;
    @Resource
    private UserService userService;

    @RequestMapping("user.do")
    public String listUserJob(@CookieValue("user") String user,Map<String,Object> model) {
        String userId = userService.getUserIdFromCookie(user);
        if(StringUtils.isBlank(userId)){
            return null;
        }
//        List<Job> jobList = jobService.searchJob(userId);
        List<Map> jobList = jobService.searchJobFromIndex(userId);
        model.put("jobList",jobList);
        return "/view/searchJobList";
    }



@RequestMapping("detail.do")
public String detail(@CookieValue("user") String user,@RequestParam("jobId")String jobId,Map<String,Object> model){
    Map job = jobService.getJob(jobId);
    if(job!=null){
        model.put("job",job);

    }
    return "/view/jobDetail";
}





}
