package com.example.springboot_learning.model.feedback;

import com.example.springboot_learning.model.responseInfo.PageParamsInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "feedback - FeedbackParamInfo 接口参数")
public class FeedbackParamInfo extends PageParamsInfo {

    @Schema(description = "意见反馈id")
    private String feedbackId;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "内容")
    private String content;
    @Schema(description = "问题类型id")
    private String questionTypeId;
    @Schema(description = "联系电话")
    private String contactTel;
    @Schema(description = "邮箱")
    private String contactEmail;

}
