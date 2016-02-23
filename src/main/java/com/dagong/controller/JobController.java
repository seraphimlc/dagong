package com.dagong.controller;

import com.dagong.pojo.Job;
import com.dagong.service.ApplyService;
import com.dagong.service.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;

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
    private VelocityLayoutViewResolver velocityLayoutViewResolver;

    @RequestMapping("user/{userId}")
    public String listUserJob(@PathVariable("userId") String userId,Map<String,Object> model) {

        List<Job> jobList = jobService.searchJob(userId);
        model.put("jobList",jobList);
        model.put("userId",userId);
        return "view/hello";
    }

    @RequestMapping("apply/{userId}/{companyId}/{jobId}")
    @ResponseBody
    public String applyJob(@PathVariable("userId") String userId,@PathVariable("companyId") String companyId,@PathVariable("jobId") String jobId,Map model){
        applyService.apply(userId,companyId,jobId);
        return "true";
    }


}
