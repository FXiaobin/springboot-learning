package com.example.springboot_learning.utils.baseErrorException;

/**
 * 自定义异常枚举类 - 实现
 */
public enum BaseErrorEnum implements BaseErrorInterface {

    OPERATION_SUCCESS("200", "操作成功"),
    NOT_FOUND("404", "访问资源不存在"),
    REQUEST_METHOD_ERROR("405", "请求方式错误"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误"),
    OPERATION_FAIL("10000", "操作失败"),
    ID_IS_EMPTY("10001", "id不能为空"),
    BODY_NOT_MATCH("10002", "数据格式不匹配"),
    USER_NOT_EXSIST("10003", "用户不存在"),
    USER_LOGIN_INVALID("10004", "当前登录信息已失效，请重新登录"),
    PARAMETER_ERROR("10005", "参数错误"),
    PASSWORD_NOT_EMPTY("10006", "密码不能为空"),
    USERNAME_NOT_EMPTY("10007", "用户名不能为空"),
    USER_ID__NOT_EMPTY("10008", "用户ID不能为空"),
    USERNAME_OR_PASSWORD_ERROR("10009", "用户名或密码错误"),
    DATA_NOT_EXSIST("10009", "数据不存在"),
    PARAMETER_PAGE_ERROR("10010", "分页参数错误"),
    TOKEN_NOT_EMPTY("10011", "token不能为空"),
    TOKEN_EXPIRED("10013", "token已过期"),
    TOKEN_INVALID("10014", "token非法"),
    TOKEN_NOT_EXSIST("10015", "token不存在"),
    TOKEN_AUTHENTICATION_FAILURE("10016", "token认证失败，访问此资源需要完全身份验证"),
    ACCESS_DENIED("10017", "访问被拒绝"),


    ;   // 最后一个用分号，分号不能省略

    /**
     * 错误码
     */
    private String code;
    /**
     * 错误提示
     */
    private String msg;

    /**
     * 构造函数
     * @param code
     * @param msg
     */
    BaseErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取错误码
     */
    @Override
    public String getCode() {
        return code;
    }

    /**
     * 获取错误提示
     */
    @Override
    public String getMsg() {
        return msg;
    }
}
