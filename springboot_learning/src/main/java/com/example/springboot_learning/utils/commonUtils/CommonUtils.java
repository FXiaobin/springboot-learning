package com.example.springboot_learning.utils.commonUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
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
}
