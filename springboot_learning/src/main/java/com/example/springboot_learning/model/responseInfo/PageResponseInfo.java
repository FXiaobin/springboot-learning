package com.example.springboot_learning.model.responseInfo;

import com.example.springboot_learning.pojo.Feedback;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
@Schema(name = "分页查询 - 数据响应实体")
public class PageResponseInfo {

    @Schema(description = "当前页码")
    private Integer pageNum;
    @Schema(description = "每页数据条数")
    private Integer pageSize;
    @Schema(description = "总记录数")
    private long total;

    public <T> PageResponseInfo(PageInfo<T> pageInfo){
//        this.pageNum = pageInfo.getPageNum();
//        this.pageSize = pageInfo.getPageSize();
//        this.total = pageInfo.getTotal();

        BeanUtils.copyProperties(pageInfo, this);
    }
}
