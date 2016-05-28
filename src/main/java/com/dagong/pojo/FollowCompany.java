package com.dagong.pojo;

import com.alibaba.fastjson.JSON;
import com.dagong.company.vo.CompanyVO;

public class FollowCompany {
    private String id;

    private String userId;

    private String companyId;

    private String info;

    private CompanyVO companyVO;

    public CompanyVO getCompanyVO() {
        if(companyVO==null){
            companyVO = JSON.parseObject(info,CompanyVO.class);
        }
        return companyVO;
    }

    public void setCompany(CompanyVO companyVO) {
        this.companyVO = companyVO;
        info = JSON.toJSONString(companyVO);
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}