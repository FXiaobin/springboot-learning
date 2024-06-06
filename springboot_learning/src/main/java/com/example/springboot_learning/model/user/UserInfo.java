package com.example.springboot_learning.model.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Schema(name = "user - UserInfo 用户详细信息类")
public class UserInfo {

//    private Integer id;
//    private String userId;
//    private String token;
//    private String userName;
//    private String nickName;
//    private String password;
//    private Integer gender;
//    private Date birthDate;
//    private String avatar;
//    private String mobile;
//    private String email;
//    private String sign;
//    private String city;
//    private Integer status;
//    private Integer membershipLevel;
//    private String identityType;
//    private Date loginTime;
//    private Date createTime;
//    private Date updateTime;

    @Schema(description = "id")
    private Integer id;

    @Schema(description = "用户id")
    private String userId;

    @Schema(description = "token")
    private String token;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "用户昵称")
    private String nickName;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "出生日期")
    private Date birthDate;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "密码")
    private String mobile;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "签名")
    private String sign;

    @Schema(description = "所在城市")
    private String city;

    @Schema(description = "用户状态 默认0，0正常， -1禁用")
    private Integer status;

    @Schema(description = "会员等级")
    private Integer membershipLevel;

    @Schema(description = "设备类型")
    private String identityType;

    @Schema(description = "登录日期")
    private Date loginTime;

    @Schema(description = "创建日期")
    private Date createTime;

    @Schema(description = "更新日期")
    private Date updateTime;

    @Schema(description = "创建日期")
    public String getcreateTimeStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = "";
        if (this.getCreateTime() != null) {
            createTime = simpleDateFormat.format(this.getCreateTime());
        }
        return createTime;
    }

    public String getUpdateTimeStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updateTimeStr = "";
        if (this.getUpdateTime() != null) {
            updateTimeStr = simpleDateFormat.format(this.getUpdateTime());
        }
        return updateTimeStr;
    }

}
