package com.example.springboot_learning.model.questionType;

import com.example.springboot_learning.utils.commonUtils.CommonUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class QuestionTypeInfo {

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


    public String getCreateTimeStr() {
        return CommonUtils.getYMDHMS(this.createTime);
    }

    public String getUpdateTimeStr() {
        return CommonUtils.getYMDHMS(this.updateTime);
    }

}
