package com.example.springboot_learning.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "question_type表： 意见反馈问题类型实体类：QuestionType", description = "意见反馈问题类型实体类")
public class QuestionType {
    @Schema(description = "id")
    private Integer id;

    @Schema(description = "问题类型名称")
    private String title;

    @Schema(description = "问题类型id")
    private String questionTypeId;

    @Schema(description = "问题类型描述")
    private String detail;

    @Schema(description = "创建人")
    private String createUser;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "创建日期")
    private Date createTime;

    @Schema(description = "更新日期")
    private Date updateTime;

    @Schema(description = "状态 0正常")
    private Integer status;


}
