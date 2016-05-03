package com.dagong.pojo;

public class ApplyRecord {
    private String id;

    private String userId;

    private String jobId;

    private Integer status;

    private Long applytime;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getApplytime() {
        return applytime;
    }

    public void setApplytime(Long applytime) {
        this.applytime = applytime;
    }
}