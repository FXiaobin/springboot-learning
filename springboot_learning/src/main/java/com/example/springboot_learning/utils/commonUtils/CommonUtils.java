package com.example.springboot_learning.utils.commonUtils;

import com.example.springboot_learning.model.responseInfo.PageParamsInfo;
import com.example.springboot_learning.pojo.Feedback;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorEnum;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CommonUtils {

    /**
     * 获取UUID 去掉横线并且小写
     * @return 469e4245c9c74e03a016753276dc1a26
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }

    /**
     * 将日期转成年月日时分秒字符串格式 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return String
     */
    public static String getYMDHMS(Date date) {
        return getDateString(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将日期转成年月日字符串 yyyy-MM-dd
     * @param date
     * @return String
     */
    public static String getYMD(Date date) {
        return getDateString(date, "yyyy-MM-dd");
    }

    /**
     * 将日期转成对应的字符串格式
     * @param date
     * @return String
     */
    public static String getDateString(Date date, String pattern) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String timeStr = "";
        if (date != null) {
            timeStr = simpleDateFormat.format(date);
        }
        return timeStr;
    }


    /**
     * 分页
     * @param list              mapper查询到的列表数据
     * @param pageParamsInfo    分页参数
     * @return                  分页后的查询数据
     * @param <T>
     */
    public static <T> List<T> getList(List<T> list, PageParamsInfo pageParamsInfo) {

        if (pageParamsInfo.getPageNum() == null || pageParamsInfo.getPageSize() == null) {
            throw new BaseErrorException(BaseErrorEnum.PARAMETER_PAGE_ERROR);
        }
        PageHelper.startPage(pageParamsInfo.getPageNum(), pageParamsInfo.getPageSize());
        PageInfo<T> infoPageInfo = new PageInfo<>(list);
        List<T> pageList = infoPageInfo.getList();

        return pageList;
    }
}
