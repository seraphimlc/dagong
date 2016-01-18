package com.dagong.service;

import com.dagong.mapper.UserMapper;
import com.dagong.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by liuchang on 16/1/17.
 */
@Service

public class UserService {
    @Resource
    private UserMapper userMapper;

    public User register(User user){
        return user;
    }

    public User getUserByUserName(String userName){
        return null;
    }
}
