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
import com.example.springboot_learning.utils.jwtUtils.JWTUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

//    @Resource
    private BCryptPasswordEncoder  bCryptPasswordEncoder;

    @Override
    public int addUser(User user) {
        if (user.getUserName() == null || user.getUserName().trim().length() == 0) {
            throw new BaseErrorException(BaseErrorEnum.USERNAME_NOT_EMPTY);
        }
        if (user.getPassword() == null || user.getPassword().trim().length() == 0) {
            throw new BaseErrorException(BaseErrorEnum.PASSWORD_NOT_EMPTY);
        }

        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);

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
        User updateUser = userMapper.selectUserByUserId(userId);
        if (updateUser == null) {
            throw new BaseErrorException(BaseErrorEnum.DATA_NOT_EXSIST);
        }
        return userMapper.disabledUser(userId);
    }

    @Override
    public int updateUser(User user) {
        if (user.getUserId() == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        User updateUser = userMapper.selectUserByUserId(user.getUserId());
        if (updateUser == null) {
            throw new BaseErrorException(BaseErrorEnum.DATA_NOT_EXSIST);
        }
        user.setUpdateTime(new Date());
        return userMapper.updateUser(user);
    }

    @Override
    public int updateUserToken(User user) {

        if (user.getUserId() == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        if (user.getToken() == null) {
            throw new BaseErrorException(BaseErrorEnum.TOKEN_NOT_EMPTY);
        }
        User updateUser = userMapper.selectUserByUserId(user.getUserId());
        if (updateUser == null) {
            throw new BaseErrorException(BaseErrorEnum.DATA_NOT_EXSIST);
        }
        user.setUpdateTime(new Date());

        return userMapper.updateUserToken(user);
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

        Map<String, String> payload = new HashMap<>();
        payload.put("userId", user.getUserId());
        payload.put("userName", user.getUserName());

        try {
            String token = JWTUtils.createToken(payload);
//            回填token
            user.setToken(token);
            int res = userMapper.updateUserToken(user);
            if (res == 1) {
//                保存成功后返回带token的用户信息
                userInfo.setToken(token);
            }

        } catch (Exception e) {
            // token生成失败
            e.printStackTrace();
        }

        return userInfo;
    }

    @Override
    public int deleteUserByUserId(String userId) {
        if (userId == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        return userMapper.deleteUserByUserId(userId);
    }
}
