package com.dagong.pojo;

import java.util.Date;

public class Job {
    private String id;

    private String companyId;

    private String detail;

    private Integer needNumber;

    private Date createTime;

    private Integer jobType;

    private Integer startsalary;

    private Integer endsalary;

    private Integer bonus;

    private Integer royalty;

    private Integer discuss;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public Integer getStartsalary() {
        return startsalary;
    }

    public void setStartsalary(Integer startsalary) {
        this.startsalary = startsalary;
    }

    public Integer getEndsalary() {
        return endsalary;
    }

    public void setEndsalary(Integer endsalary) {
        this.endsalary = endsalary;
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
}