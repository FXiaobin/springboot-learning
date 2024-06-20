package com.example.springboot_learning.utils.securityUtils;

import com.example.springboot_learning.model.responseInfo.ResponseInfo;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorEnum;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import netscape.javascript.JSObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {


    //token认证失败 访问此资源需要完全身份验证
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

//        Map map = new HashMap<>();
//        map.put("code", "401");
//        map.put("msg", "token认证失败,访问此资源需要完全身份验证");
//        response.getWriter().write(map.toString());

        ResponseInfo responseInfo = ResponseInfo.responseInfoException(new BaseErrorException(BaseErrorEnum.TOKEN_AUTHENTICATION_FAILURE));
        ObjectMapper jacksonObjectMapper = new ObjectMapper();
        String jsonString = jacksonObjectMapper.writer().writeValueAsString(responseInfo);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(jsonString);

//        response.getWriter().flush();
    }
}

