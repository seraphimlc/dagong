package com.dagong.mapper;

import com.dagong.pojo.FollowUser;

public interface FollowUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(FollowUser record);

    int insertSelective(FollowUser record);

    FollowUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FollowUser record);

    int updateByPrimaryKey(FollowUser record);
}