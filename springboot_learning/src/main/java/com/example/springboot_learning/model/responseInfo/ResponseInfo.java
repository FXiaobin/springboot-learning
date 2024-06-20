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
    @Schema(description = "分页响应数据")
    public PageResponseInfo pageInfo;

    /**
     * 通用返回数据格式
     * @param code
     * @param msg
     * @param data
     * @return  ResponseInfo
     */
    public static ResponseInfo responseInfo(String code, String msg, Object data, PageResponseInfo pageResponseInfo) {
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.code = code;
        responseInfo.msg = msg;
        responseInfo.data = data;
        responseInfo.pageInfo = pageResponseInfo;

        return responseInfo;
    }

    /**
     *  操作成功
     * @param data
     * @return  ResponseInfo
     */
    public static ResponseInfo responseInfoSuccess(Object data) {
        return ResponseInfo.responseInfoSuccess(data, null);
    }

    /**
     *  操作成功
     * @param data
     * @return  ResponseInfo
     */
    public static ResponseInfo responseInfoSuccess(Object data, PageResponseInfo pageResponseInfo) {
        return ResponseInfo.responseInfo(BaseErrorEnum.OPERATION_SUCCESS.getCode(), BaseErrorEnum.OPERATION_SUCCESS.getMsg(), data, pageResponseInfo);
    }

    /**
     * 操作成功 / 操作失败
     * @param isSuc 是否成功
     * @param data
     * @return  ResponseInfo
     */
    public static ResponseInfo responseInfoResult(Boolean isSuc, Object data) {
        return ResponseInfo.responseInfoResult(isSuc, data, null);
    }

    /**
     * 操作成功 / 操作失败
     * @param isSuc 是否成功
     * @param data
     * @return  ResponseInfo
     */
    public static ResponseInfo responseInfoResult(Boolean isSuc, Object data, PageResponseInfo pageResponseInfo) {
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
        responseInfo.pageInfo = pageResponseInfo;

        return responseInfo;
    }


    /**
     * 抛出异常对应的返回数据
     * @param e 异常对象
     * @return  ResponseInfo
     */
    public static ResponseInfo responseInfoException(BaseErrorException e) {
        return ResponseInfo.responseInfo(e.getCode(), e.getMsg(), null, null);
    }


    /**
     * 根据错误枚举生成错误响应信息。
     *
     * 该方法通过错误枚举对象生成一个包含错误代码和错误消息的响应信息对象，用于统一错误响应的格式。
     * 错误枚举（BaseErrorEnum）提供了错误代码和错误消息的映射，使得在生成响应信息时不需要硬编码错误代码和消息。
     *
     * @param e 错误枚举对象，包含具体的错误代码和错误消息。
     * @return 响应信息对象，包含错误代码、错误消息以及可能的其他字段（本方法中未设置）。
     */
    public static ResponseInfo responseInfoErrorEnum(BaseErrorEnum e) {
        // 调用ResponseInfo类的静态方法生成包含错误代码、错误消息的响应信息对象
        return ResponseInfo.responseInfo(e.getCode(), e.getMsg(), null, null);
    }


}
