package com.dagong.service;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.dagong.job.vo.JobVO;
import com.dagong.mapper.ApplyLogMapper;
import com.dagong.mapper.ApplyRecordMapper;
import com.dagong.mq.SendMessageService;
import com.dagong.pojo.ApplyLog;
import com.dagong.pojo.ApplyRecord;
import com.dagong.util.IdGenerator;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liuchang on 16/1/17.
 */
@Service
public class ApplyService {
    private static int STATUS_INIT = 1;
    private static int STATUS_READ = 2;
    private static int STATUS_ACCEPT = 3;
    private static int STATUS_REFUSE = 4;
    private static int STATUS_INTERVIEW = 5;
    private static int STATUS_SUCCESS = 6;
    private static int STATUS_FAILED = 7;
    private static int PAGE_SIZE = 10;

    @Resource
    private ApplyLogMapper applyLogMapper;

    @Resource
    private ApplyRecordMapper applyRecordMapper;

    @Resource
    private JobService jobService;


    @Resource
    private IdGenerator idGenerator;

    @Resource
    private SendMessageService sendMessageService;

    public boolean apply(String userId, String companyId, String jobId) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        JobVO jobVO = jobService.getJob(jobId);
        if (jobVO == null || !jobVO.getCompanyId().equals(companyId)) {
            return false;
        }
        ApplyRecord applyRecord = createApplyRecord(userId, jobVO);
        ApplyLog applyLog = createApplyLog(userId, applyRecord.getId(), applyRecord.getStatus(), applyRecord.getStatus());
        applyRecordMapper.insert(applyRecord);
        applyLogMapper.insert(applyLog);

        Map<String, Object> msgMap = new HashedMap();
        msgMap.put("userId", userId);
        msgMap.put("jobId", jobId);
        msgMap.put("companyId", companyId);
        msgMap.put("applyTime", applyRecord.getApplyTime());
        msgMap.put("applyId",applyRecord.getId());
        sendMessageService.sendMessage("job","userApplyJob", msgMap);
        return true;
    }


    public List<ApplyRecord> selectApplyRecordByUserId(String userId, int page, int status) {
        if (page <= 0) {
            page = 1;
        }
        PageHelper.startPage(page, PAGE_SIZE);
        Page<ApplyRecord> pages = (Page<ApplyRecord>) applyRecordMapper.selectByUserId(userId, status);

        return pages;
    }

//    public boolean read( String companyUserId,String applyId) {
//        return updateStatus(companyUserId, applyId, STATUS_READ);
//    }
//
//    public boolean accept(String companyUserId, String applyId) {
//        return updateStatus( companyUserId, applyId, STATUS_ACCEPT);
//    }
//
//    public boolean refuse(String companyUserId,String applyId) {
//        return updateStatus(companyUserId, applyId, STATUS_REFUSE);
//    }
//
//    public boolean interview(String companyUserId,String applyId) {
//        return updateStatus(companyUserId, applyId, STATUS_INTERVIEW);
//    }
//
//    public boolean success(String companyId, String companyUserId,String applyId) {
//        return updateStatus( companyUserId, applyId, STATUS_SUCCESS);
//    }
//
//    public boolean fail(String companyUserId, String applyId) {
//        return updateStatus( companyUserId, applyId, STATUS_FAILED);
//    }

    public boolean updateStatus(String companyUserId, String applyId, int status) {
        ApplyRecord applyRecord = applyRecordMapper.selectByPrimaryKey(applyId);
        ApplyRecord newApplyRecord = new ApplyRecord();
        newApplyRecord.setId(applyId);
        newApplyRecord.setStatus(status);
        newApplyRecord.setUpdateTime(System.currentTimeMillis());
        ApplyLog applyLog = createApplyLog(companyUserId, applyId, applyRecord.getStatus(), newApplyRecord.getStatus());
        applyRecordMapper.updateByPrimaryKeySelective(newApplyRecord);
        applyLogMapper.insert(applyLog);
        return true;
    }

    private ApplyLog createApplyLog(String userId, String applyId, int oldStatus, int newStatus) {
        ApplyLog applyLog = new ApplyLog();
        applyLog.setId(idGenerator.generate(ApplyLog.class.getSimpleName()));
        applyLog.setLogTime(new Date());
        applyLog.setApplyId(applyId);
        applyLog.setModifyUser(userId);
        applyLog.setNewStatus(newStatus);
        applyLog.setOldStatus(oldStatus);
        return applyLog;
    }

    private ApplyRecord createApplyRecord(String userId, JobVO jobVO) {
        ApplyRecord applyRecord = new ApplyRecord();
        applyRecord.setStatus(STATUS_INIT);
        applyRecord.setUserId(userId);
        applyRecord.setJobId(jobVO.getId());
        applyRecord.setApplyTime(System.currentTimeMillis());
        applyRecord.setUpdateTime(System.currentTimeMillis());
        applyRecord.setId(idGenerator.generate(ApplyRecord.class.getSimpleName()));
        try {
            applyRecord.setDescription(JSON.json(jobVO));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return applyRecord;
    }


}
