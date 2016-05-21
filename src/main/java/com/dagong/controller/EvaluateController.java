package com.dagong.controller;

import com.alibaba.fastjson.JSON;
import com.dagong.evaluation.vo.CompanyEvaluationVO;
import com.dagong.evaluation.vo.JobEvaluationVO;
import com.dagong.evaluation.vo.VOList;
import com.dagong.service.EvaluationService;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by liuchang on 16/5/21.
 */
@RestController
public class EvaluateController {

    private static int DEFAULT_PAGE_SIZE=0;

   @Resource
   private EvaluationService evaluationService;

    @RequestMapping("/evaluateJob.do")
    public String evaluateJob(@CookieValue("userId") String userId,
                              @RequestParam("jobId") String jobId,
                              @RequestParam("rank") int rank,
                              @RequestParam("comment") String comment) {
        evaluationService.evaluateJob(userId, jobId, rank, comment);
        return null;
    }

    public String evaluateCompany(@CookieValue("userId") String userId,
                                  @RequestParam("companyId") String companyId,
                                  @RequestParam("rank") int rank,
                                  @RequestParam("comment") String comment) {
        evaluationService.evaluateCompany(userId, companyId, rank, comment);
        return null;
    }

    public String getJobEvaluation(@CookieValue("userId") String userId,
                                   @RequestParam("jobId") String jobId,
                                   @RequestParam(value = "page",defaultValue = "1")int page) {
        List<Map> list = evaluationService.getJobEvaluation(jobId, page, DEFAULT_PAGE_SIZE);
        return JSON.toJSONString(list);
    }

    public String getCompanyEvaluation(@CookieValue("userId") String userId,
                                       @RequestParam("companyId") String companyId,
                                       @RequestParam(value = "page",defaultValue = "1")int page) {
        List<Map> list = evaluationService.getCompanyEvaluation(companyId, page, DEFAULT_PAGE_SIZE);
        return JSON.toJSONString(list);
    }

    public String getUserEvaluation(@CookieValue("userId") String userId,
                                    @RequestParam(value = "page",defaultValue = "1")int page) {
        List<Map> list = evaluationService.getUserEvaluation(userId, page, DEFAULT_PAGE_SIZE);
        return JSON.toJSONString(list);
    }

    public String getMyJobEvaluation(@CookieValue("userId") String userId,
                                     @RequestParam(value = "page",defaultValue = "1")int page) {
        VOList<JobEvaluationVO> list = evaluationService.getJobEvaluationForUser(userId, page, DEFAULT_PAGE_SIZE);
        return JSON.toJSONString(list);
    }

    public String getMyCompanyEvaluation(@CookieValue("userId") String userId,
                                         @RequestParam(value = "page",defaultValue = "1")int page) {
        VOList<CompanyEvaluationVO> list = evaluationService.getCompanyEvaluationForUser(userId, page, DEFAULT_PAGE_SIZE);
        return JSON.toJSONString(list);
    }

}
