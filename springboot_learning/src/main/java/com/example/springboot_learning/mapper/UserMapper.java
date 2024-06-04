package com.example.springboot_learning.mapper;

import com.example.springboot_learning.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> selectAllUserList();

    User selectUserByUserId(String userId);

}
