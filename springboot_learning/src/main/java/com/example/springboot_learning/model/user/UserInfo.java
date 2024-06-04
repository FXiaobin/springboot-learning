package com.example.springboot_learning.model.user;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class UserInfo {

    private Integer id;
    private String userId;
    private String token;
    private String userName;
    private String nickName;
    private String password;
    private Integer gender;
    private Date birthDate;
    private String avatar;
    private String mobile;
    private String email;
    private String sign;
    private String city;
    private Integer status;
    private Integer membershipLevel;
    private String identityType;
    private Date loginTime;
    private Date createTime;
    private Date updateTime;

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
