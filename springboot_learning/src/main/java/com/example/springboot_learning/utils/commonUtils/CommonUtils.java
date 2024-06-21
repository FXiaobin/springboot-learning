package com.example.springboot_learning.utils.commonUtils;

import com.example.springboot_learning.model.responseInfo.PageParamsInfo;
import com.example.springboot_learning.pojo.Feedback;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorEnum;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
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


    /**
     * 获取本地主机的IP地址。
     * <p>
     * 本方法旨在获取当前设备的本地IP地址，而非公网IP地址。它通过调用InetAddress.getLocalHost()方法来获取本地主机地址，
     * 并返回该地址的主机部分。需要注意的是，本地主机可能有多个网络接口（如IPv4和IPv6），因此返回的IP地址可能取决于环境配置。
     * </p>
     *
     * @return 本地主机的IP地址字符串。
     * @throws UnknownHostException 如果无法确定本地主机名称或IP地址无法解析。
     */
    public static String getLocalIpAddress() throws UnknownHostException {
        // 获取本地主机的InetAddress对象
        InetAddress address = InetAddress.getLocalHost();
        // 返回本地主机地址的字符串表示
        return address.getHostAddress();
    }


    /**
     * 获取客户端ip地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }

        }
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }







}
