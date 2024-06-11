package com.example.springboot_learning.mapper;

import com.example.springboot_learning.model.banner.BannerParamInfo;
import com.example.springboot_learning.model.responseInfo.PageParamsInfo;
import com.example.springboot_learning.pojo.Banner;

import java.util.List;

public interface BannerMapper {

    int addBanner(Banner banner);

    int updateBanner(Banner banner);

    int disabledBanner(String bannerId);

    List<Banner> selectAllBannerList(PageParamsInfo pageParamsInfo);

    Banner selectBannerByBannerId(String bannerId);

    List<Banner> selecBannerByBannerParamInfo(BannerParamInfo bannerParamInfo);

    int deleteBannerByBannerId(String bannerId);
    
}
