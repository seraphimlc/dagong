package com.dagong.service;

import com.dagong.mapper.*;
import com.dagong.pojo.User;
import com.dagong.pojo.UserExperience;
import com.dagong.pojo.UserSkill;
import com.dagong.util.IdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by liuchang on 16/1/17.
 */
@Service

public class UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserExperienceMapper userExperienceMapper;

    @Resource
    private UserSkillMapper userSkillMapper;

    @Resource
    private WantJobMapper wantJobMapper;

    @Resource
    private WantEnvironmentMapper wantEnvironmentMapper;

    @Resource
    private WantInformationMapper wantInformationMapper;

    @Resource
    private IdGenerator idGenerator;

    public User register(String userName, String cardid, String email, String phone, String wechat, String qq,
                         String password, Date birthday){
        User user = new User();
        user.setId(idGenerator.generate(User.class.getSimpleName()));
        user.setUsername(userName);
        user.setCardid(cardid);
        user.setEmail(email);
        user.setPhone(phone);
        user.setWechat(wechat);
        user.setQq(qq);
        user.setPassword(password);
        user.setBrithday(birthday);
        userMapper.insert(user);
        return user;
    }

    public User getUserByUserName(String userName){
        return null;
    }

    public boolean saveUserSkill(String userId,String skill){
        UserSkill userSkill = userSkillMapper.selectByUserId(userId);
        if(userSkill==null){
            userSkill = new UserSkill();
            userSkill.setId(idGenerator.generate(UserSkill.class.getSimpleName()));
            userSkill.setUserId(userId);
            userSkill.setSkill(skill);
            userSkillMapper.insert(userSkill);
        }else{
            userSkill.setSkill(skill);
            userSkillMapper.updateByPrimaryKey(userSkill);
        }
        return true;
    }

    public boolean saveUserExperience(String userId,String experience){
        UserExperience userExperience = userExperienceMapper.selectByUserId(userId);
        if(userExperience==null){
            userExperience = new UserExperience();
            userExperience.setId(idGenerator.generate(UserExperience.class.getSimpleName()));
            userExperience.setUserId(userId);
            userExperience.setExperience(experience);
            userExperienceMapper.insertSelective(userExperience);
        }else{
            userExperience.setExperience(experience);
            userExperienceMapper.updateByPrimaryKey(userExperience);
        }
        return true;
    }
}
