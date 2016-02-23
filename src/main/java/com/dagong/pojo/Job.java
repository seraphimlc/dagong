package com.dagong.pojo;

import java.io.Serializable;
import java.util.Date;

public class Job implements Serializable{

    private static final long serialVersionUID = 2357446539254499488L;
    private String id;

    private String jobName;

    private String companyId;

    private String detail;

    private Integer needNumber;

    private Date createTime;

    private Integer jobType;

    private Integer startSalary;

    private Integer endSalary;

    private Integer bonus;

    private Integer royalty;

    private Integer discuss;

    private Date modifyTime;

    private Date startTime;

    private Date endTime;

    private String modifyUser;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Integer getNeedNumber() {
        return needNumber;
    }

    public void setNeedNumber(Integer needNumber) {
        this.needNumber = needNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public Integer getStartSalary() {
        return startSalary;
    }

    public void setStartSalary(Integer startSalary) {
        this.startSalary = startSalary;
    }

    public Integer getEndSalary() {
        return endSalary;
    }

    public void setEndSalary(Integer endSalary) {
        this.endSalary = endSalary;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public Integer getRoyalty() {
        return royalty;
    }

    public void setRoyalty(Integer royalty) {
        this.royalty = royalty;
    }

    public Integer getDiscuss() {
        return discuss;
    }

    public void setDiscuss(Integer discuss) {
        this.discuss = discuss;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser == null ? null : modifyUser.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                ", jobName='" + jobName + '\'' +
                ", companyId='" + companyId + '\'' +
                ", detail='" + detail + '\'' +
                ", needNumber=" + needNumber +
                ", createTime=" + createTime +
                ", jobType=" + jobType +
                ", startSalary=" + startSalary +
                ", endSalary=" + endSalary +
                ", bonus=" + bonus +
                ", royalty=" + royalty +
                ", discuss=" + discuss +
                ", modifyTime=" + modifyTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", modifyUser='" + modifyUser + '\'' +
                ", status=" + status +
                '}';
    }
}