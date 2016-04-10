package com.dagong.controller;

import com.dagong.service.FollowService;
import com.dagong.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by liuchang on 16/1/28.
 */
@RestController
//@RequestMapping("/follow")
public class FollowController {
    @Resource
    private FollowService followService;
    @Resource
    private UserService userService;


    @RequestMapping("/follow.do")
    @ResponseBody
    public String applyJob(@CookieValue("user") String user,@RequestParam("jobId") String jobId) {
        String userId = userService.getUserIdFromCookie(user);
        followService.followJob(userId, jobId);
        return "true";
    }

}
