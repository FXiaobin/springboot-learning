package com.example.springboot_learning.serviceImpl;

import com.example.springboot_learning.mapper.BannerMapper;
import com.example.springboot_learning.model.banner.BannerInfo;
import com.example.springboot_learning.model.banner.BannerParamInfo;
import com.example.springboot_learning.model.responseInfo.PageParamsInfo;
import com.example.springboot_learning.model.responseInfo.PageResponseInfo;
import com.example.springboot_learning.model.responseInfo.ResponseInfo;
import com.example.springboot_learning.pojo.Banner;
import com.example.springboot_learning.service.BannerService;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorEnum;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorException;
import com.example.springboot_learning.utils.commonUtils.CommonUtils;
import com.example.springboot_learning.utils.convertUtils.BannerConvertUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public int addBanner(Banner banner) {
        banner.setBannerId(CommonUtils.getUUID());
        banner.setSort(0);
        banner.setStatus(0);
        banner.setCreateTime(new Date());
        banner.setUpdateTime(new Date());

        if (banner.getTitle() == null) {
            throw new BaseErrorException(BaseErrorEnum.PARAMETER_ERROR);
        }

        return bannerMapper.addBanner(banner);
    }

    @Override
    public int updateBanner(Banner banner) {
        if (banner.getBannerId() == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        Banner updateBanner = bannerMapper.selectBannerByBannerId(banner.getBannerId());
        if (updateBanner == null) {
            throw new BaseErrorException(BaseErrorEnum.DATA_NOT_EXSIST);
        }
        banner.setUpdateTime(new Date());
        return bannerMapper.updateBanner(banner);
    }

    @Override
    public BannerInfo selectBannerByBannerId(String bannerId) {
        if (bannerId == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        Banner banner = bannerMapper.selectBannerByBannerId(bannerId);
        return  BannerConvertUtils.bannerInfoByBanner(banner);
    }

    @Override
    public int disabledBanner(String bannerId) {
        if (bannerId == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        Banner updateBanner = bannerMapper.selectBannerByBannerId(bannerId);
        if (updateBanner == null) {
            throw new BaseErrorException(BaseErrorEnum.DATA_NOT_EXSIST);
        }
        return bannerMapper.disabledBanner(bannerId);
    }

    /**
     * 根据分页参数查询所有反馈信息列表。
     *
     * @param pageParamsInfo 分页参数信息，包含当前页码和每页大小。
     * @return 包含反馈信息列表和分页信息的响应对象。
     */
    @Override
    public ResponseInfo selectAllBannerList(PageParamsInfo pageParamsInfo) {

        // ************* PageHelper插件 分页处理 *************

        // 分页参数可传么不传  不传的时候为查询所有数据
        if (pageParamsInfo.getPageNum() != null && pageParamsInfo.getPageSize() != null) {
            // 初始化分页插件（这里初始化分页后，紧跟着的一条mybatis查询方法就会被自动分页处理，注意：是紧跟着的后面的第一个mybatis查询会被分页）
            PageHelper.startPage(pageParamsInfo.getPageNum(), pageParamsInfo.getPageSize());
        }

        // 查询分页对应的反馈信息列表（插件会自动处理分页查询，这里甚至不用传分页参数pageParamsInfo）

        List<Banner> bannerList = bannerMapper.selectAllBannerList(pageParamsInfo);

        // 构建分页信息
        PageInfo<Banner> pageInfo = new PageInfo<>(bannerList);

        // 将反馈信息转换为反馈详情列表
        List<BannerInfo> bannerInfoList = BannerConvertUtils.bannerInfoListByBannerList(bannerList);

        // 构建分页响应信息
        PageResponseInfo pageResponseInfo = new PageResponseInfo(pageInfo);

        // 构建并返回成功响应信息，包含反馈详情列表和分页信息
        ResponseInfo responseInfo = ResponseInfo.responseInfoSuccess(bannerInfoList, pageResponseInfo);

        return responseInfo;
    }

    @Override
    public ResponseInfo selecBannerByBannerParamInfo(BannerParamInfo bannerParamInfo) {
        BannerParamInfo paramInfo = new BannerParamInfo();
        paramInfo.setBannerId(bannerParamInfo.getBannerId());
        paramInfo.setStatus(bannerParamInfo.getStatus());
        paramInfo.setType(bannerParamInfo.getType());
        paramInfo.setPageNum(bannerParamInfo.getPageNum());
        paramInfo.setPageSize(bannerParamInfo.getPageSize());

        if (bannerParamInfo.getTitle() != null && !bannerParamInfo.getTitle().isEmpty()) {
            paramInfo.setTitle("%" + bannerParamInfo.getTitle() + "%");
        }

        // 分页参数可传么不传  不传的时候为查询所有数据
        if (paramInfo.getPageNum() != null && paramInfo.getPageSize() != null) {
            // 初始化分页插件（这里初始化分页后，紧跟着的一条mybatis查询方法就会被自动分页处理，注意：是紧跟着的后面的第一个mybatis查询会被分页）
            PageHelper.startPage(paramInfo.getPageNum(), paramInfo.getPageSize());
        }

        List<Banner> bannerList = bannerMapper.selecBannerByBannerParamInfo(paramInfo);
        // 构建分页信息
        PageInfo<Banner> pageInfo = new PageInfo<>(bannerList);

        PageResponseInfo pageResponseInfo = new PageResponseInfo(pageInfo);
        List<BannerInfo> bannerInfoList = BannerConvertUtils.bannerInfoListByBannerList(bannerList);

        // 构建并返回成功响应信息，包含反馈详情列表和分页信息
        ResponseInfo responseInfo = ResponseInfo.responseInfoSuccess(bannerInfoList, pageResponseInfo);


        return responseInfo;
    }

    @Override
    public int deleteBannerByBannerId(String bannerId) {
        if (bannerId == null) {
            throw new BaseErrorException(BaseErrorEnum.USER_ID__NOT_EMPTY);
        }
        return bannerMapper.deleteBannerByBannerId(bannerId);
    }
}

