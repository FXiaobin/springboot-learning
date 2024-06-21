package com.example.springboot_learning.utils.securityUtils;

import com.example.springboot_learning.serviceImpl.SecurityUserDetailService;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorEnum;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorException;
import com.example.springboot_learning.utils.commonUtils.CommonUtils;
import com.example.springboot_learning.utils.jwtUtils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT过滤器 为了在用户每次请求时验证JWT，我们需要创建一个自定义的过滤器
 */
@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private SecurityUserDetailService securityUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求的ip地址
        String ip = CommonUtils.getIpAddr(request);

        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            logger.info("jwtToken: " + jwtToken);
            if (jwtToken != null && jwtToken.length() > 0) {
                try {
                    username = JWTUtils.getUserNameByToken(jwtToken);
                    logger.info("username: " + username);

                } catch (Exception e) {

                    throw new BaseErrorException(BaseErrorEnum.TOKEN_INVALID);
//                    e.printStackTrace();
                }
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = securityUserDetailService.loadUserByUsername(username);
            if (JWTUtils.verify(jwtToken).getToken().equals(jwtToken)) {
                /**
                 * 这里省略了查询数据库token存不存在,有没有失效这个步骤
                 * 如果是做单点登录,后登录的人登录时候把上一个人的token状态改为失效,这样就能保证同一时间一个帐号只能有一个人能使用
                 */
                // 存入SecurityContextHolder
                // 获取权限信息，封装到Authentication
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        // 放行
        filterChain.doFilter(request, response);
    }



}
