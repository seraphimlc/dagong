package com.dagong.mapper;

import com.dagong.pojo.ApplyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplyRecordMapper {

    int deleteByPrimaryKey(String id);

    int insert(ApplyRecord record);

    int insertSelective(ApplyRecord record);

    ApplyRecord selectByPrimaryKey(String id);

    List<ApplyRecord> selectByUserId(@Param("userId") String userId, @Param("status") Integer status);

    int updateByPrimaryKeySelective(ApplyRecord record);

    int updateByPrimaryKey(ApplyRecord record);
}