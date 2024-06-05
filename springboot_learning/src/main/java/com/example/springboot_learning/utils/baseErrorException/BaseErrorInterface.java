package com.example.springboot_learning.utils.baseErrorException;

/**
 * 自定义异常枚举类 - 接口
 */
public interface BaseErrorInterface {

    /**
     * 获取错误码
     */
    String getCode();

    /**
     * 获取错误提示
     */
    String getMsg();

}
