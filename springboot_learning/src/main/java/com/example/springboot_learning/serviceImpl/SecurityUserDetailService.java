package com.example.springboot_learning.serviceImpl;

import com.example.springboot_learning.mapper.UserMapper;
import com.example.springboot_learning.model.user.SecurityUserDetails;
import com.example.springboot_learning.pojo.User;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorEnum;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SecurityUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名从数据库中查询用户
        User user = userMapper.selectUserByUserName(username);
        if (user == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_NOT_EXSIST);
        }

        SecurityUserDetails securityUserDetails = new SecurityUserDetails(user);

        return securityUserDetails;
    }
}
