package com.dagong.controller;

import com.dagong.service.ApplyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by liuchang on 16/1/28.
 */
@RestController
@RequestMapping("/apply")
public class ApplyController {
    @Resource
    private ApplyService applyService;

    @RequestMapping("/{jobId}/{companyId}/{userId}")
    public String show(String jobId, String companyId, String userId) {

        return applyService.apply(userId, companyId, jobId) + "";
    }

}
