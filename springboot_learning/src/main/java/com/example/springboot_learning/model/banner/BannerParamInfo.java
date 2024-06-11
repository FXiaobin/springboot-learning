package com.example.springboot_learning.model.banner;

import com.example.springboot_learning.model.responseInfo.PageParamsInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "轮播图 相关接口参数信息 BannerParamInfo")
public class BannerParamInfo extends PageParamsInfo {

    @Schema(description = "banner_id")
    private String bannerId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "类型")
    private Integer type;

    @Schema(description = "状态 0正常")
    private Integer status;

    @Schema(description = "创建人")
    private String createUser;


}
