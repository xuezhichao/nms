package com.byxf.nms.model;

public enum OperateMsg {

    SAVEORUPDATEOSNEWINFO("001","官网新闻配置信息保存"),
    QUERYOSNEWINFO("002","查询官网新闻配置信息详情"),
    QUERYOSNEWINFOSBYPAGE("003","分页查询官网新闻配置信息列表"),
    DELETEOSNEWINFOS("004","删除新闻信息"),
    SAVEORUPDATEOSNEWSTATUSINFO("005","新闻发布"),
    UPLOADHEADERIMAGE("006","上传封面"),
    UPLOAD("007","上传文件"),
    ;

    private String code;

    private String msg;

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

    OperateMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static OperateMsg getByCode(String code) {
        OperateMsg[] arr$ = values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            OperateMsg enums = arr$[i$];
            if (enums.getCode().equals(code)) {
                return enums;
            }
        }
        return null;
    }
}
