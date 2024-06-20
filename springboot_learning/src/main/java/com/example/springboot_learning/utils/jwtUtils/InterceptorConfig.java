package com.example.springboot_learning.utils.jwtUtils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器到Spring MVC的拦截器注册表中。
     *
     * 本方法的目的是为了配置一个拦截器，该拦截器会对所有请求进行拦截，除了登录接口。
     * 通过拦截器，我们可以实现诸如JWT令牌验证等预处理逻辑，以确保请求的安全性。
     *
     * @param registry 拦截器注册表，用于添加新的拦截器及其配置。
     */
    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        // 添加JWTInterceptor拦截器，该拦截器负责处理JWT令牌的验证。
        registry.addInterceptor(new JWTInterceptor())
                //表示拦截所有请求,现在访问所有请求都是空页面
                .addPathPatterns("/**")
                // 排除登录接口，使其不受JWT拦截器的影响。
                // 但是此时访问静态页面,所有的图片和CSS样式都是加载不出来的
                // 加上/**之后可以进行拦截多级目录
                .excludePathPatterns("/**/*.html")
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/**/*.js")
                .excludePathPatterns("/**/*.png")
                .excludePathPatterns("/**/api-docs/**")     // swagger-ui.index被重定向为 /api-dev/v3/api-docs
                .excludePathPatterns("/user/addUser")
                .excludePathPatterns("/user/loginWithUserNamePassword")
                .excludePathPatterns("/error")
        ;

    }




}
