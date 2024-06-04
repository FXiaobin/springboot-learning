package com.example.springboot_learning.controller;

import com.example.springboot_learning.model.responseInfo.ResponseInfo;
import com.example.springboot_learning.model.user.UserParamInfo;
import com.example.springboot_learning.pojo.User;
import com.example.springboot_learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/selectAllUserList", method = RequestMethod.POST)
    public ResponseInfo selectAllUserList() {
        List<User> userList = userService.selectAllUserList();
        ResponseInfo responseInfo = ResponseInfo.responseInfoSuccess(userList);

        return responseInfo;
    }

    @RequestMapping(value = "/selectUserByUserId", method = RequestMethod.POST)
    public ResponseInfo selectUserByUserId(@RequestBody UserParamInfo userParamInfo) {
        User user = userService.selectUserByUserId(userParamInfo.getUserId());
        ResponseInfo responseInfo = ResponseInfo.responseInfoSuccess(user);

        return responseInfo;
    }






}
