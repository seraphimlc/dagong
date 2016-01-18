package com.dagong.mapper;

import com.dagong.pojo.JobStatus;

public interface JobStatusMapper {
    int deleteByPrimaryKey(String id);

    int insert(JobStatus record);

    int insertSelective(JobStatus record);

    JobStatus selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JobStatus record);

    int updateByPrimaryKey(JobStatus record);
}