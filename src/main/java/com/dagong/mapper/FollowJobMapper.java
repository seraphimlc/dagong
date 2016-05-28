package com.dagong.mapper;

import com.dagong.pojo.FollowJob;

import java.util.List;

public interface FollowJobMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(FollowJob record);

    FollowJob selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FollowJob record);

    List<FollowJob> getListSelective(FollowJob followJob);
}