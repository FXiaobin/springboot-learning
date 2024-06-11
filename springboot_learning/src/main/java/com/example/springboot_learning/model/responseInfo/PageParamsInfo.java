package com.example.springboot_learning.model.responseInfo;

import com.github.pagehelper.IPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "分页查询 - 请求参数实体")
public class PageParamsInfo implements IPage {

    @Schema(description = "当前页码，不传时默认为1")
    private Integer pageNum = 1;
    @Schema(description = "每页数据条数，不传时默认为10")
    private Integer pageSize = 10;
    @Schema(description = "排序")
    private String orderBy;


}
