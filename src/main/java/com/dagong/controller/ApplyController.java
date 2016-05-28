package com.dagong.controller;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.dagong.service.ApplyService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuchang on 16/1/28.
 */
@RestController
//@RequestMapping("/apply")
public class ApplyController {
    @Resource
    private ApplyService applyService;

    @RequestMapping("apply.do")
    @ResponseBody
    public String applyJob(@CookieValue("userId") String userId, @RequestParam("companyId") String companyId, @RequestParam("jobId") String jobId) {
        try {
            applyService.apply(userId, companyId, jobId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }
        return "true";
    }

    @RequestMapping("/apply/userApply.do")
    public ModelAndView showUserApplyRecord(@CookieValue("userId") String userId, @RequestParam(value = "page",defaultValue = "1") int page) {
        List allList = applyService.selectApplyRecordByUserId(userId, page, 0);
        List readList = applyService.selectApplyRecordByUserId(userId, page, 1);
        List unReadList = applyService.selectApplyRecordByUserId(userId, page, 2);
        List passList = applyService.selectApplyRecordByUserId(userId, page, 3);
        List unPassList = applyService.selectApplyRecordByUserId(userId, page, 4);
        ModelAndView modelAndView = new ModelAndView("view/applyList");
        modelAndView.addObject("allList",allList);
        modelAndView.addObject("readList",readList);
        modelAndView.addObject("unReadList",unReadList);
        modelAndView.addObject("passList",passList);
        modelAndView.addObject("unPassList",unPassList);
        return modelAndView;

    }



}
