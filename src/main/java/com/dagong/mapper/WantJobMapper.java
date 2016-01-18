package com.dagong.mapper;

import com.dagong.pojo.WantJob;

public interface WantJobMapper {
    int deleteByPrimaryKey(String id);

    int insert(WantJob record);

    int insertSelective(WantJob record);

    WantJob selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WantJob record);

    int updateByPrimaryKey(WantJob record);
}