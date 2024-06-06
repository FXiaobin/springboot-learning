package com.example.springboot_learning.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "feedback - Feedback 意见反馈实体类")
public class Feedback {

    @Schema(description = "id")
    private Integer id;
    @Schema(description = "意见反馈id")
    private String feedbackId;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "内容")
    private String content;
    @Schema(description = "问题类型id")
    private String questionTypeId;
    @Schema(description = "创建人")
    private String createUser;
    @Schema(description = "联系电话")
    private String contactTel;
    @Schema(description = "邮箱")
    private String contactEmail;
    @Schema(description = "图片列表")
    private String imageUrls;
    @Schema(description = "状态 0正常")
    private Integer status;
    @Schema(description = "排序")
    private Integer sort;
    @Schema(description = "创建日期")
    private Date createTime;
    @Schema(description = "更新日期")
    private Date updateTime;




}
