package com.example.springboot_learning.utils.convertUtils;


import com.example.springboot_learning.model.banner.BannerInfo;
import com.example.springboot_learning.pojo.Banner;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class BannerConvertUtils {
    /**
     * 将Banner数据库获取的实体对象转成对应处理后接口要返回的实体数据
     * @param banner
     * @return bannerInfo
     */
    public static BannerInfo bannerInfoByBanner(Banner banner) {
        BannerInfo bannerInfo = new BannerInfo();
        BeanUtils.copyProperties(banner, bannerInfo);
        return bannerInfo;
    }

    /**
     * 将Banner数据库获取的实体对象列表转成对应处理后接口要返回的实体数据列表
     * @param bannerList
     * @return bannerInfoList
     */
    public static List<BannerInfo> bannerInfoListByBannerList(List<Banner> bannerList) {

        List<BannerInfo> bannerInfoList = new ArrayList<>();
        for (Banner banner : bannerList) {
            BannerInfo bannerInfo = bannerInfoByBanner(banner);
            bannerInfoList.add(bannerInfo);
        }
        return bannerInfoList;
    }
}
