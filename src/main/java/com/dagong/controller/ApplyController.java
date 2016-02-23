package com.dagong.controller;

import com.alibaba.fastjson.JSON;
import com.dagong.pojo.Job;
import com.dagong.service.ApplyService;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    public static void main(String[] args) {
        Job job = new Job();
        job.setCompanyId("aaaa");
        job.setModifyUser("bbbb");
        job.setId("asdfasdfasdfsf");
        Job job1 = new Job();
        job1.setCompanyId("aaaa");
        job1.setModifyUser("bbbb");
        job1.setId("asdfasdfasdfsf");
        List list = Lists.newArrayList();
        list.add(job);
        list.add(job1);
        String json;
        json = JSON.toJSONString(list);

        System.out.println("job = " + json);
        list = JSON.parseArray(json,Job.class);
        System.out.println("list = " + list);
        System.out.println("list = " + list.get(0));

    }
}
