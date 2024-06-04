package com.example.springboot_learning.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;
    private String userId;
    private String token;
    private String userName;
    private String nickName;
    private String password;
    private String gender;
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





}
