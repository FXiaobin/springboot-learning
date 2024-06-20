package com.example.springboot_learning.utils.securityUtils;

import com.example.springboot_learning.model.responseInfo.ResponseInfo;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorEnum;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    //角色权限认证失败
     @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ResponseInfo responseInfo = ResponseInfo.responseInfoException(new BaseErrorException(BaseErrorEnum.ACCESS_DENIED));
        ObjectMapper jacksonObjectMapper = new ObjectMapper();
        String jsonString = jacksonObjectMapper.writer().writeValueAsString(responseInfo);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(jsonString);

//        response.getWriter().flush();
    }



}
