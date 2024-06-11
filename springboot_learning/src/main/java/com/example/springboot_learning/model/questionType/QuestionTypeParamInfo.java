package com.example.springboot_learning.model.questionType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "意见反馈问题类型相关接口参数信息 QuestionTypeParamInfo")
public class QuestionTypeParamInfo {

    @Schema(description = "问题类型名称")
    private String title;

    @Schema(description = "问题类型id")
    private String questionTypeId;

    @Schema(description = "问题类型描述")
    private String detail;

    @Schema(description = "创建人")
    private String createUser;

    @Schema(description = "状态 0正常")
    private Integer status;
}
