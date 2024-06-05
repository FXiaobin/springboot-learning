package com.example.springboot_learning.utils.baseErrorException;

import com.example.springboot_learning.model.responseInfo.ResponseInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常捕获处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 当访问接口使用自定义的BaseErrorException类抛出异常时，会触发handleMyCustomException方法来捕获异常，此方法将返回自定义的错误信息和状态码（通过 @ResponseBody写出）。
     * 利用 Spring 的 Exception 机制，捕获到指定异常后，将需要写出的数据，通过 @ResponseBody写出
     * @param e 自定义异常对象
     * @return 接口统一返回对象ResponseInfo
     */
    @ResponseBody
    @ExceptionHandler(BaseErrorException.class)
    public ResponseInfo handleMyCustomException(BaseErrorException e) {
        return ResponseInfo.responseInfoException(e);
    }
}
