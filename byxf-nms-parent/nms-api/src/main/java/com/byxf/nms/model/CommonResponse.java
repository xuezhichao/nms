package com.byxf.nms.model;

public class CommonResponse {

    private String responseNo;

    private String getResponseTime;

    private String version;

    private String code;

    private String msg;

    private String data;

    public String getResponseNo() {
        return responseNo;
    }

    public void setResponseNo(String responseNo) {
        this.responseNo = responseNo;
    }

    public String getGetResponseTime() {
        return getResponseTime;
    }

    public void setGetResponseTime(String getResponseTime) {
        this.getResponseTime = getResponseTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public CommonResponse(String responseNo, String getResponseTime, String version, String code, String msg,
            String data) {
        this.responseNo = responseNo;
        this.getResponseTime = getResponseTime;
        this.version = version;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public CommonResponse() {
    }
}
