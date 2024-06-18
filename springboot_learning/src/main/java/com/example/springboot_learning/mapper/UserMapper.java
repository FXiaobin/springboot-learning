package com.example.springboot_learning.mapper;

import com.example.springboot_learning.model.user.UserParamInfo;
import com.example.springboot_learning.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface UserMapper {

    int addUser(User user);

    int updateUser(User user);

    int updateUserToken(User user);

    int disabledUser(String userId);

    List<User> selectAllUserList();

    User selectUserByUserId(String userId);

    User selectUserByUserName(String userName);

    List<User> selecUserByUserParamInfo(UserParamInfo userParamInfo);

    int deleteUserByUserId(String userId);

}
