package com.example.springboot_learning.service;

import com.example.springboot_learning.model.banner.BannerInfo;
import com.example.springboot_learning.model.banner.BannerParamInfo;
import com.example.springboot_learning.model.responseInfo.PageParamsInfo;
import com.example.springboot_learning.model.responseInfo.ResponseInfo;
import com.example.springboot_learning.pojo.Banner;

import java.util.List;

public interface BannerService {

    int addBanner(Banner banner);

    int updateBanner(Banner banner);

    BannerInfo selectBannerByBannerId(String bannerId);

    int disabledBanner(String bannerId);

    ResponseInfo selectAllBannerList(PageParamsInfo pageParamsInfo);

    ResponseInfo selecBannerByBannerParamInfo(BannerParamInfo bannerParamInfo);

    int deleteBannerByBannerId(String bannerId);

}
