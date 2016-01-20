package com.dagong.service.impl;

import com.dagong.service.IFollowService;

import java.util.List;

/**
 * Created by liuchang on 16/1/19.
 */
public class FollowService implements IFollowService {
    @Override
    public boolean follow(String followerId, String followedId) {
        return false;
    }

    @Override
    public boolean dismiss(String followId, String followerId, String followedId) {
        return false;
    }

    @Override
    public List getFollow(String followId, String followerId, String followedId) {
        return null;
    }
}
