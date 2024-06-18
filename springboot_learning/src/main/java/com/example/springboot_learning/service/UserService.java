package com.example.springboot_learning.service;

import com.example.springboot_learning.model.user.UserInfo;
import com.example.springboot_learning.model.user.UserParamInfo;
import com.example.springboot_learning.model.user.UserParamLoginInfo;
import com.example.springboot_learning.pojo.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    int addUser(User user);

    int disabledUser(String userId);

    int updateUser(User user);

    int updateUserToken(User user);

    List<UserInfo> selectAllUserList();

    UserInfo selectUserByUserId(String userId);

    List<UserInfo> selecUserByUserParamInfo(UserParamInfo userParamInfo);

    UserInfo loginWithUserNamePassword(UserParamLoginInfo userParamLoginInfo);

    int deleteUserByUserId(String userId);
}
