package com.example.springboot_learning.model.feedback;

import com.example.springboot_learning.utils.commonUtils.CommonUtils;
import lombok.Data;

import java.util.Date;

@Data
public class FeedbackInfo {

    private Integer id;
    private String feedbackId;
    private String title;
    private String content;
    private String questionTypeId;
    private String createUser;
    private String contactTel;
    private String contactEmail;
    private String imageUrls;
    private Integer status;
    private Integer sort;
    private Date createTime;
    private Date updateTime;

    public String getCreateTimeStr() {
        return CommonUtils.getYMDHMS(this.createTime);
    }

    public String getUpdateTimeStr() {
        return CommonUtils.getYMDHMS(this.updateTime);
    }
}
