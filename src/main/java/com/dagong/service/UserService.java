package com.dagong.service;

import com.dagong.mapper.*;
import com.dagong.pojo.*;
import com.dagong.util.IdGenerator;
import com.dagong.util.ListUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by liuchang on 16/1/17.
 */
@Service

public class UserService {
    private static int MAX_WANT_JOB = 5;
    private static int MAX_WANT_ENVIRONMENT = 5;
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
                         String password, Date birthday) {
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

    public User getUserByUserName(String userName) {
        return null;
    }

    public boolean saveUserSkill(String userId, String skill) {
        UserSkill userSkill = userSkillMapper.selectByUserId(userId);
        if (userSkill == null) {
            userSkill = new UserSkill();
            userSkill.setId(idGenerator.generate(UserSkill.class.getSimpleName()));
            userSkill.setUserId(userId);
            userSkill.setSkill(skill);
            userSkillMapper.insert(userSkill);
        } else {
            userSkill.setSkill(skill);
            userSkillMapper.updateByPrimaryKey(userSkill);
        }
        return true;
    }

    public boolean saveUserExperience(String userId, String experience) {
        UserExperience userExperience = userExperienceMapper.selectByUserId(userId);
        if (userExperience == null) {
            userExperience = new UserExperience();
            userExperience.setId(idGenerator.generate(UserExperience.class.getSimpleName()));
            userExperience.setUserId(userId);
            userExperience.setExperience(experience);
            userExperienceMapper.insertSelective(userExperience);
        } else {
            userExperience.setExperience(experience);
            userExperienceMapper.updateByPrimaryKey(userExperience);
        }
        return true;
    }

    public boolean addWantJob(String userId, int[] jobIds) {
        List<WantJob> wantJobs = wantJobMapper.selectByUserId(userId);
        int existsSize = ListUtil.isEmpty(wantJobs) ? 0 : wantJobs.size();
        for (int i = 0; i < Math.min(MAX_WANT_JOB - existsSize, jobIds.length); i++) {
            WantJob wantJob = new WantJob();
            wantJob.setId(idGenerator.generate(WantJob.class.getSimpleName()));
            wantJob.setUserId(userId);
            wantJob.setJobType(jobIds[i]);
            wantJobMapper.insertSelective(wantJob);
        }
        return true;
    }

    public boolean clearWantJob(String userId) {
        wantJobMapper.deleteByUserId(userId);
        return true;
    }

    public boolean addWantEnvironment(String userId, int[] envIds) {
        List<WantEnvironment> wantEnvironments = wantEnvironmentMapper.selectByUserId(userId);
        int existsSize = ListUtil.isEmpty(wantEnvironments) ? 0 : wantEnvironments.size();
        for (int i = 0; i < Math.min(MAX_WANT_ENVIRONMENT - existsSize, envIds.length); i++) {
            WantEnvironment wantEnvironment = new WantEnvironment();
            wantEnvironment.setId(idGenerator.generate(WantEnvironment.class.getSimpleName()));
            wantEnvironment.setUserId(userId);
            wantEnvironment.setEnvId(envIds[i]);
            wantEnvironmentMapper.insertSelective(wantEnvironment);
//            WantJob wantJob = new WantJob();
//            wantJob.setId(idGenerator.generate(WantJob.class.getSimpleName()));
//            wantJob.setUserId(userId);
//            wantJob.setJobType(jobIds[i]);
//            wantJobMapper.insertSelective(wantJob);
        }
        return true;
    }

    public boolean clearWantJobs(String userId) {
        wantEnvironmentMapper.deleteByUserId(userId);
        return true;
    }

    public boolean saveWantInformation(String userId, UserInformationKey userInformationKey, String value) {
        WantInformation wantInformation = wantInformationMapper.selectByUserIdAndKey(userId, userInformationKey.getName());
        if (wantInformation == null) {
            wantInformation = new WantInformation();
            wantInformation.setId(idGenerator.generate(WantInformation.class.getSimpleName()));
            wantInformation.setUserId(userId);
            wantInformation.setKey(userInformationKey.getName());
            wantInformation.setValue(value);
            wantInformationMapper.insert(wantInformation);
        } else {
            wantInformation.setValue(value);
            wantInformationMapper.updateByPrimaryKeySelective(wantInformation);
        }
        return true;
    }

}
