package com.dagong.service;

import java.util.List;

/**
 * Created by liuchang on 16/1/18.
 */
public interface IFollowService<T> {
    public boolean follow(String followerId,String followedId);
    public boolean dismiss(String followId,String followerId,String followedId);
    public List<T> getFollow(String followId,String followerId,String followedId);

}
