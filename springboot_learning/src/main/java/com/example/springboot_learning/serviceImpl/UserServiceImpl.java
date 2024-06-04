package com.example.springboot_learning.serviceImpl;

import com.example.springboot_learning.mapper.UserMapper;
import com.example.springboot_learning.pojo.User;
import com.example.springboot_learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAllUserList() {
        return userMapper.selectAllUserList();
    }

    @Override
    public User selectUserByUserId(String userId) {
        return userMapper.selectUserByUserId(userId);
    }
}
