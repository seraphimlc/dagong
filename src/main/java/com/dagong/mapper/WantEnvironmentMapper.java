package com.dagong.mapper;

import com.dagong.pojo.WantEnvironment;

public interface WantEnvironmentMapper {
    int deleteByPrimaryKey(String id);

    int insert(WantEnvironment record);

    int insertSelective(WantEnvironment record);

    WantEnvironment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WantEnvironment record);

    int updateByPrimaryKey(WantEnvironment record);
}