package com.example.springboot_learning.service;

import com.example.springboot_learning.model.user.UserInfo;
import com.example.springboot_learning.model.user.UserParamInfo;
import com.example.springboot_learning.pojo.User;

import java.util.List;

public interface UserService {

    int addUser(User user);

    int disabledUser(String userId);

    int updateUser(User user);

    List<UserInfo> selectAllUserList();

    UserInfo selectUserByUserId(String userId);

    List<UserInfo> selecUserByUserParamInfo(UserParamInfo userParamInfo);
}
