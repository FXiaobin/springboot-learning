package com.example.springboot_learning.model.banner;

import com.example.springboot_learning.utils.commonUtils.CommonUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "banner表： 轮播图实体类：Banner", description = "轮播图实体类")
public class BannerInfo {

    @Schema(description = "id")
    private Integer id;

    @Schema(description = "banner_id")
    private String bannerId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "图片地址")
    private String img;

    @Schema(description = "跳转地址")
    private String url;

    @Schema(description = "类型")
    private Integer type;

    @Schema(description = "状态 0正常")
    private Integer status;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "创建人")
    private String createUser;

    @Schema(description = "创建日期")
    private Date createTime;

    @Schema(description = "更新日期")
    private Date updateTime;

    public String getCreateTimeStr() {
        return CommonUtils.getYMDHMS(this.createTime);
    }

    public String getUpdateTimeStr() {
        return CommonUtils.getYMDHMS(this.updateTime);
    }

}
