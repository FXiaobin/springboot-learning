package com.example.springboot_learning.service;

import com.example.springboot_learning.pojo.User;

import java.util.List;

public interface UserService {

    List<User> selectAllUserList();

    User selectUserByUserId(String userId);
}
