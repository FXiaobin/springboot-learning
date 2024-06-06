package com.example.springboot_learning.model.responseInfo;

import com.example.springboot_learning.utils.baseErrorException.BaseErrorEnum;
import com.example.springboot_learning.utils.baseErrorException.BaseErrorException;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "请求响应结果")
public class ResponseInfo {

//    这里的属性需要用public， 不然会报错

    @Schema(description = "响应码")
    public String code;
    @Schema(description = "响应描述")
    public String msg;
    @Schema(description = "响应数据")
    public Object data;

    /**
     * 通用返回数据格式
     * @param code
     * @param msg
     * @param data
     * @return  ResponseInfo
     */
    public static ResponseInfo responseInfo(String code, String msg, Object data) {
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.code = code;
        responseInfo.msg = msg;
        responseInfo.data = data;
        return responseInfo;
    }

    /**
     *  操作成功
     * @param data
     * @return  ResponseInfo
     */
    public static ResponseInfo responseInfoSuccess(Object data) {
        return ResponseInfo.responseInfo(BaseErrorEnum.OPERATION_SUCCESS.getCode(), BaseErrorEnum.OPERATION_SUCCESS.getMsg(), data);
    }

    /**
     * 操作成功 / 操作失败
     * @param isSuc 是否成功
     * @param data
     * @return  ResponseInfo
     */
    public static ResponseInfo responseInfoResult(Boolean isSuc, Object data) {
        ResponseInfo responseInfo = new ResponseInfo();
        if (isSuc) {
            // 操作成功
            responseInfo.code = BaseErrorEnum.OPERATION_SUCCESS.getCode();
            responseInfo.msg = BaseErrorEnum.OPERATION_SUCCESS.getMsg();
        }else {
            // 操作失败
            responseInfo.code = BaseErrorEnum.OPERATION_FAIL.getCode();
            responseInfo.msg = BaseErrorEnum.OPERATION_FAIL.getMsg();
        }
        responseInfo.data = data;
        return responseInfo;
    }


    /**
     * 抛出异常对应的返回数据
     * @param e 异常对象
     * @return  ResponseInfo
     */
    public static ResponseInfo responseInfoException(BaseErrorException e) {
        return ResponseInfo.responseInfo(e.getCode(), e.getMsg(), null);
    }


}
