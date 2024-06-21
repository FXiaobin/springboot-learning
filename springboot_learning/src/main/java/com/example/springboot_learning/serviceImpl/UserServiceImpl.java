package com.example.springboot_learning.serviceImpl;

import com.example.springboot_learning.mapper.UserMapper;
import com.example.springboot_learning.model.responseInfo.ResponseInfo;
import com.example.springboot_learning.model.user.SecurityUserDetails;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    /**
     * slf4j日志
     */
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 这里的authenticationManager其实是SecurityConfig配置类中的
     * @Bean public AuthenticationManager authenticationManager(){}
     * 可以SecurityConfig配置类中的方法注释掉发现会报错
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 这里的bcryptPasswordEncoder其实是SecurityConfig配置类中的
     * @Bean public BCryptPasswordEncoder bcryptPasswordEncoder(){}
     * 可以SecurityConfig配置类中的方法注释掉发现会报错
     */
    @Autowired
    BCryptPasswordEncoder bcryptPasswordEncoder;

    @Override
    public int addUser(User user) {
        if (user.getUserName() == null || user.getUserName().trim().length() == 0) {
            throw new BaseErrorException(BaseErrorEnum.USERNAME_NOT_EMPTY);
        }
        if (user.getPassword() == null || user.getPassword().trim().length() == 0) {
            throw new BaseErrorException(BaseErrorEnum.PASSWORD_NOT_EMPTY);
        }

        // 密码加密
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String password = passwordEncoder.encode(user.getPassword());

        String password = bcryptPasswordEncoder.encode(user.getPassword());
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
        logger.debug("+++ slf4j debug: selectUserByUserId = {}", userId);
        System.out.println("+++ slf4j println: selectUserByUserId = " + userId);
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

        // 这里我们不再自己查询数据库 改成 SecurityUserDetailService来查询认证
//        List<User> userList = userMapper.selecUserByUserParamInfo(userParamInfo);
//        if (userList == null || userList.size() == 0) {
//            throw new BaseErrorException(BaseErrorEnum.DATA_NOT_EXSIST);
//        }
//        User user = userList.get(0);

        //AuthenticationManager authenticate 进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userParamLoginInfo.getUserName(), userParamLoginInfo.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (authentication == null) {
            throw new BaseErrorException(BaseErrorEnum.USERNAME_OR_PASSWORD_ERROR);    // 认证失败
        }

        SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        String userId = user.getUserId(); // 后面用来Redis存储的  暂时先不用
        //把完整的用户信息存入redis userid作为key
//        redisCache.setCacheObject("login:"+userId,loginUser);
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
    public ResponseInfo logout() {
        // 获取SecurityContextHolder中存储的用户id
        UsernamePasswordAuthenticationToken authentication =(UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        String userId = user.getUserId();
        // 删除redis中的值
//        redisCache.deleteObject("login"+userId);

//        清空登出用户token
        user.setToken(null);
        userMapper.updateUserToken(user);

        return ResponseInfo.responseInfoErrorEnum(BaseErrorEnum.OPERATION_SUCCESS);
    }

    @Override
    public int deleteUserByUserId(String userId) {
        if (userId == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        return userMapper.deleteUserByUserId(userId);
    }


}
