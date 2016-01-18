package com.dagong.mapper;

import com.dagong.pojo.WantInformation;

public interface WantInformationMapper {
    int deleteByPrimaryKey(String id);

    int insert(WantInformation record);

    int insertSelective(WantInformation record);

    WantInformation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WantInformation record);

    int updateByPrimaryKey(WantInformation record);
}