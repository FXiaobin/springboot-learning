package com.example.springboot_learning.model.user;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "user - UserParamInfo 接口参数")
public class UserParamInfo {

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "邮箱")
    private String email;
}
