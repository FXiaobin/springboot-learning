package com.example.springboot_learning.model.user;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name= "user - UserParamLoginInfo 登录接口请求参数")
public class UserParamLoginInfo {

    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userName;

    @Schema(description= "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

}
