package com.dagong.controller;

import com.dagong.service.ApplyService;
import com.dagong.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by liuchang on 16/1/28.
 */
@RestController
//@RequestMapping("/apply")
public class ApplyController {
    @Resource
    private ApplyService applyService;
    @Resource
    private UserService userService;


    @RequestMapping("/apply.do")
    @ResponseBody
    public String applyJob(@CookieValue("userId") String userId, @RequestParam("companyId") String companyId, @RequestParam("jobId") String jobId) {
        applyService.apply(userId, companyId, jobId);
        return "true";
    }

}
