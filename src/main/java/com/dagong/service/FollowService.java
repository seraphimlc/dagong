package com.dagong.service;

import com.dagong.mapper.FollowCompanyMapper;
import com.dagong.mapper.FollowJobMapper;
import com.dagong.mapper.FollowUserMapper;
import com.dagong.pojo.FollowCompany;
import com.dagong.pojo.FollowJob;
import com.dagong.util.IdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuchang on 16/1/17.
 */
@Service

public class FollowService {
    @Resource
    private FollowCompanyMapper followCompanyMapper;
    @Resource
    private FollowJobMapper followJobMapper;
    @Resource
    private FollowUserMapper followUserMapper;

    @Resource
    private IdGenerator idGenerator;

    public boolean followCompany(String userId, String companyId) {
        List<FollowCompany> followCompanies = getFollowCompany(userId, companyId);
        if (null == followCompanies || followCompanies.isEmpty()) {
            FollowCompany followCompany = createFollowCompany(userId, companyId);
            followCompanyMapper.insert(followCompany);
        }
        return true;
    }

    private FollowCompany createFollowCompany(String userId, String companyId) {
        FollowCompany followCompany = new FollowCompany();
        followCompany.setCompanyId(idGenerator.generate(FollowCompany.class.getSimpleName()));
        followCompany.setCompanyId(companyId);
        followCompany.setUserId(userId);
        return followCompany;
    }

    public List<FollowCompany> getFollowCompany(String userId, String companyId) {
        FollowCompany followCompany = new FollowCompany();
        followCompany.setUserId(userId);
        followCompany.setCompanyId(companyId);
        return followCompanyMapper.selectFollowCompanySelective(followCompany);

    }

    public boolean dismissFollowCompany(String followId, String userId, String companyId) {
        List<FollowCompany> followCompanies = getFollowCompany(userId, companyId);
        {
            if (followCompanies != null && !followCompanies.isEmpty()) {
                for (FollowCompany followCompany : followCompanies) {
                    if (followCompany.getId().equals(followId)) {
                        followCompanyMapper.deleteByPrimaryKey(followId);
                    }
                }
            }
        }
        return true;
    }

    public boolean followJob(String userId,String jobId){
        List<FollowJob> followJobs = getFollowJob(userId, jobId);
        if (null == followJobs || followJobs.isEmpty()) {
            FollowJob followJob = createFollowJob(userId, jobId);
            followJobMapper.insert(followJob);
        }
        return true;
    }

    public boolean dismissFollowJob(String followId,String userId,String jobId){
        List<FollowJob> followJobs = getFollowJob(userId, jobId);
        if(followJobs!=null&&!followJobs.isEmpty()){
            for(FollowJob followJob:followJobs){
                if(followJob.getId().equals(followId)){
                    followJobMapper.deleteByPrimaryKey(followId);
                }
            }
        }
        return true;
    }

    private FollowJob createFollowJob(String userId, String jobId) {
        FollowJob followJob = new FollowJob();
        followJob.setJobId(jobId);
        followJob.setUserId(userId);
        followJob.setId(idGenerator.generate(FollowJob.class.getSimpleName()));
        return followJob;
    }

    public List<FollowJob> getFollowJob(String userId,String jobId){
        FollowJob followJob = new FollowJob();
        followJob.setUserId(userId);
        followJob.setJobId(jobId);
        return followJobMapper.selectFollowJobSelective(followJob);
    }


}
