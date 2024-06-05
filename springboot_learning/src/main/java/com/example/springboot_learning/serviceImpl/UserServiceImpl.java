package com.example.springboot_learning.serviceImpl;

import com.example.springboot_learning.mapper.UserMapper;
import com.example.springboot_learning.model.user.UserInfo;
import com.example.springboot_learning.model.user.UserParamInfo;
import com.example.springboot_learning.model.user.UserParamLoginInfo;
import com.example.springboot_learning.pojo.User;
import com.example.springboot_learning.service.UserService;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorEnum;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorException;
import com.example.springboot_learning.utils.commonUtils.CommonUtils;
import com.example.springboot_learning.utils.convertUtils.UserConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        user.setUserId(CommonUtils.getUUID());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        return userMapper.addUser(user);
    }

    @Override
    public int disabledUser(String userId) {
        if (userId == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        return userMapper.disabledUser(userId);
    }

    @Override
    public int updateUser(User user) {
        if (user.getUserId() == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        user.setUpdateTime(new Date());
        return userMapper.updateUser(user);
    }

    @Override
    public List<UserInfo> selectAllUserList() {
        List<User> userList = userMapper.selectAllUserList();
        List<UserInfo> userInfoList = UserConvertUtils.userInfoListByUserList(userList);
        return userInfoList;
    }

    @Override
    public UserInfo selectUserByUserId(String userId) {
        if (userId == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        User user = userMapper.selectUserByUserId(userId);
        return  UserConvertUtils.userInfoByUser(user);
    }

    @Override
    public List<UserInfo> selecUserByUserParamInfo(UserParamInfo userParamInfo) {
        List<User> userList = userMapper.selecUserByUserParamInfo(userParamInfo);
        List<UserInfo> userInfoList = UserConvertUtils.userInfoListByUserList(userList);
        return userInfoList;
    }

    @Override
    public UserInfo loginWithUserNamePassword(UserParamLoginInfo userParamLoginInfo) {
        if (userParamLoginInfo.getUserName() == null) {
            throw new BaseErrorException(BaseErrorEnum.USERNAME_NOT_EMPTY);
        }
        if (userParamLoginInfo.getPassword() == null) {
            throw new BaseErrorException(BaseErrorEnum.PASSWORD_NOT_EMPTY);
        }
        UserParamInfo userParamInfo = new UserParamInfo();
        userParamInfo.setUserName(userParamLoginInfo.getUserName());
        userParamInfo.setPassword(userParamLoginInfo.getPassword());
        List<User> userList = userMapper.selecUserByUserParamInfo(userParamInfo);
        if (userList == null || userList.size() == 0) {
            throw new BaseErrorException(BaseErrorEnum.DATA_NOT_EXSIST);
        }
        User user = userList.get(0);
        UserInfo userInfo = UserConvertUtils.userInfoByUser(user);
        return userInfo;
    }


}
