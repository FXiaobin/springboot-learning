package com.example.springboot_learning.controller;

import com.example.springboot_learning.model.banner.BannerInfo;
import com.example.springboot_learning.model.banner.BannerParamInfo;
import com.example.springboot_learning.model.responseInfo.PageParamsInfo;
import com.example.springboot_learning.model.responseInfo.ResponseInfo;
import com.example.springboot_learning.pojo.Banner;
import com.example.springboot_learning.service.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
@Tag(name = "轮播图banner", description = "轮播图接口")
public class BannerController {

    @Autowired
    private BannerService bannerService;


    /*
     *   总结：
     *   1. 请求参数是一个对象，所以就算只有一个参数也要放到一个对象中，这里就需要创建一个参数实体类了；
     *   2. 请求参数需要添加@RequestBody注解，否则接口接收不到参数传递过来的数据
     *   3. 最好使用RequestMapping来设置value和method，否则可能有问题
     *
     * */

    /**
     * 新增轮播图
     * @param banner
     * @return
     */
    @Operation(summary = "轮播图-新增", description = "用于新增一条轮播图信息")
    @RequestMapping(value = "/addBanner", method = RequestMethod.POST)
    public ResponseInfo addBanner(@RequestBody Banner banner) {
        int result = bannerService.addBanner(banner);
        ResponseInfo responseInfo = ResponseInfo.responseInfoResult(result == 1, banner);

        return responseInfo;
    }

    @Operation(summary = "轮播图-修改轮播图信息")
    @RequestMapping(value = "/updateBanner", method = RequestMethod.PUT)
    public ResponseInfo updateBanner(@RequestBody Banner banner) {
        int result = bannerService.updateBanner(banner);
        ResponseInfo responseInfo = ResponseInfo.responseInfoResult(result == 1, null);

        return responseInfo;
    }

    @Operation(summary = "轮播图-禁用某个轮播图")
    @RequestMapping(value = "/disabledBanner", method = RequestMethod.PUT)
    public ResponseInfo disabledBanner(@RequestBody BannerParamInfo bannerParamInfo) {
        int result = bannerService.disabledBanner(bannerParamInfo.getBannerId());
        ResponseInfo responseInfo = ResponseInfo.responseInfoResult(result == 1, null);

        return responseInfo;
    }


    @Operation(summary = "轮播图-根据bannerId查询轮播图信息")
    @RequestMapping(value = "/selectBannerByBannerId", method = RequestMethod.POST)
    public ResponseInfo selectBannerByBannerId(@RequestBody BannerParamInfo bannerParamInfo) {
        BannerInfo bannerInfo = bannerService.selectBannerByBannerId(bannerParamInfo.getBannerId());
        ResponseInfo responseInfo = ResponseInfo.responseInfoSuccess(bannerInfo);

        return responseInfo;
    }

    @Operation(summary = "轮播图-查询所有轮播图")
    @RequestMapping(value = "/selectAllBannerList", method = RequestMethod.GET)
    public ResponseInfo selectAllBannerList(PageParamsInfo pageParamsInfo) {
//        PageInfo<Banner> pageInfo =
//        List<BannerInfo> bannerInfoList = BannerConvertUtils.bannerInfoListByBannerList(pageInfo.getList());
//        ResponseInfo responseInfo = ResponseInfo.responseInfoSuccess(bannerInfoList);

        return bannerService.selectAllBannerList(pageParamsInfo);
    }

    @Operation(summary = "轮播图-按条件查询轮播图信息")
    @RequestMapping(value = "/selecBannerByBannerParamInfo", method = RequestMethod.POST)
    public ResponseInfo selecBannerByBannerParamInfo(@RequestBody BannerParamInfo bannerParamInfo) {
        return bannerService.selecBannerByBannerParamInfo(bannerParamInfo);
    }

    @Operation(summary = "轮播图-轮播图永久删除 慎用！！！")
    @RequestMapping(value = "/deleteBannerByBannerId", method = RequestMethod.DELETE)
    public ResponseInfo deleteBannerByBannerId(@RequestBody BannerParamInfo bannerParamInfo ) {
        int result = bannerService.deleteBannerByBannerId(bannerParamInfo.getBannerId());
        ResponseInfo responseInfo = ResponseInfo.responseInfoResult(result == 1, null);

        return responseInfo;
    }


}
