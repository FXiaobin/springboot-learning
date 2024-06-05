package com.example.springboot_learning.utils.baseErrorException;

/**
 * 自定义异常处理类
 */
public class BaseErrorException extends RuntimeException {
    /**
     * 错误码
     */
    private String code;
    /**
     * 错误提示
     */
    private String msg;

//    RuntimeException异常类本身有一个message属性，这里我们没有使用它，而是使用我们自己定义的属性msg
//    private String message;

    public BaseErrorException() {
        super();
    }

    /**
     * 根据异常枚举 --> 获取自定义异常对象
     * @param baseErrorEnum 自定义异常枚举
     */
    public BaseErrorException(BaseErrorEnum baseErrorEnum) {
        super(baseErrorEnum.getMsg());
        this.code = baseErrorEnum.getCode();
        this.msg = baseErrorEnum.getMsg();
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }
}
