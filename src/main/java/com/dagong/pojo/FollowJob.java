package com.dagong.pojo;

import com.alibaba.fastjson.JSON;
import com.dagong.job.vo.JobVO;

public class FollowJob {
    private String id;

    private String userId;

    private String jobId;

    private String info;

    private JobVO jobVO;

    public JobVO getJobVO() {
        if(jobVO==null){
            jobVO = JSON.parseObject(info,JobVO.class);
        }
        return jobVO;
    }

    public void setJobVO(JobVO jobVO) {
        this.jobVO = jobVO;
        info = JSON.toJSONString(jobVO);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}