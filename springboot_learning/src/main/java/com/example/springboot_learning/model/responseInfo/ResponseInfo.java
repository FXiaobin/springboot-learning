package com.example.springboot_learning.model.responseInfo;

public class ResponseInfo {

    public String code;
    public String msg;
    public Object data;

    public static ResponseInfo responseInfo(String code, String msg, Object data) {
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.code = code;
        responseInfo.msg = msg;
        responseInfo.data = data;

        return responseInfo;
    }

    public static ResponseInfo responseInfoResult(Boolean isSuc, Object data) {
        ResponseInfo responseInfo = new ResponseInfo();
        if (isSuc) {
            responseInfo.code = "200";
            responseInfo.msg = "操作成功";
        }else{
            responseInfo.code = "0";
            responseInfo.msg = "操作失败";
        }
        responseInfo.data = data;

        return responseInfo;
    }

    public static ResponseInfo responseInfoSuccess(Object data) {
        return ResponseInfo.responseInfoResult(true, data);
    }


}
