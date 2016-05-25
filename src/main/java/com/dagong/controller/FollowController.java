package com.dagong.controller;

import com.dagong.pojo.FollowJob;
import com.dagong.service.FollowService;
import com.dagong.service.UserService;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by liuchang on 16/1/28.
 */
@RestController
//@RequestMapping("/follow")
public class FollowController {
    private static final int PAGE_SIZE=10;
    @Resource
    private FollowService followService;
    @Resource
    private UserService userService;


    @RequestMapping("/followJob.do")
    @ResponseBody
    public String followJob(@CookieValue("userId") String userId,@RequestParam("jobId") String jobId) {
        followService.followJob(userId, jobId);
        return "true";
    }

    @RequestMapping("/followCompany.do")
    @ResponseBody
    public String followCompany(@CookieValue("userId") String userId,@RequestParam("companyId") String companyId) {
        followService.followCompany(userId, companyId);
        return "true";
    }

    @RequestMapping("/getFollowJob.do")
    public Page getFollowJob(@CookieValue("userId") String userId,@RequestParam(name="page",defaultValue = "1")int page){
        Page<FollowJob> followJobForUser = followService.getFollowJobForUser(userId, page, PAGE_SIZE);
        return followJobForUser;
    }



}
