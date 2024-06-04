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

    public static ResponseInfo responseInfoSuccess(Object data) {
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.code = "200";
        responseInfo.msg = "success";
        responseInfo.data = data;

        return responseInfo;
    }


}
