package com.example.springboot_learning.model.user;

import com.example.springboot_learning.pojo.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Data
public class SecurityUserDetails implements UserDetails {

    private User user;

    public SecurityUserDetails(User user) {
        // 从数据查询到的用户信息
        this.user = user;
    }


    // 重写父类UserDetails的方法，实现UserDetails接口中的方法
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    // 用户拥有的权限集合，我这里先设置为null，将来会再更改的
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    //    后面四个方法都是用户是否可用、是否过期之类的。我都设置为true
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 检查当前功能是否启用。
     *
     * 默认情况下，此方法返回true，表示功能启用。子类可以重写此方法来改变默认行为，
     * 根据特定条件决定是否启用某功能。这提供了一种灵活的方式来动态控制功能的可用性，
     * 而不需要修改调用代码。
     *
     * @return 如果功能启用返回true，否则返回false。默认返回true。
     */
    @Override
    public boolean isEnabled() {
//        return user.getStatus() == 0;
        return true;
    }


}
