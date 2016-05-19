package com.dagong.mapper;

import com.dagong.pojo.Invitation;

public interface InvitationMapper {
    int insert(Invitation record);

    int insertSelective(Invitation record);
}