package com.example.springboot_learning.utils.convertUtils;

import com.example.springboot_learning.model.user.UserInfo;
import com.example.springboot_learning.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class UserConvertUtils {

    /**
     * 将User数据库获取的实体对象转成对应处理后接口要返回的实体数据
     * @param user
     * @return userInfo
     */
    public static UserInfo userInfoByUser(User user) {

        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setUserId(user.getUserId());
        userInfo.setToken(user.getToken());
        userInfo.setUserName(user.getUserName());
        userInfo.setNickName(user.getNickName());
        userInfo.setPassword(user.getPassword());
        userInfo.setGender(user.getGender());
        userInfo.setBirthDate(user.getBirthDate());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setMobile(user.getMobile());
        userInfo.setEmail(user.getEmail());
        userInfo.setSign(user.getSign());
        userInfo.setCity(user.getCity());
        userInfo.setStatus(user.getStatus());
        userInfo.setMembershipLevel(user.getMembershipLevel());
        userInfo.setIdentityType(user.getIdentityType());
        userInfo.setLoginTime(user.getLoginTime());
        userInfo.setCreateTime(user.getCreateTime());
        userInfo.setUpdateTime(user.getUpdateTime());

        return userInfo;
    }

    /**
     * 将User数据库获取的实体对象列表转成对应处理后接口要返回的实体数据列表
     * @param userList
     * @return userInfoList
     */
    public static List<UserInfo> userInfoListByUserList(List<User> userList) {

        List<UserInfo> userInfoList = new ArrayList<>();
        for (User user : userList) {
            UserInfo userInfo = userInfoByUser(user);
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }
    
}
