package com.dagong.mapper;

import com.dagong.pojo.UserSkill;

public interface UserSkillMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserSkill record);

    int insertSelective(UserSkill record);

    UserSkill selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserSkill record);

    int updateByPrimaryKey(UserSkill record);
}