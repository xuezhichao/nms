package com.byxf.nms.model;


public enum BusinessSucCode {

    EXEC_SUCC("00000", "操作成功"),
    ;

    private String code;
    private String desc;

    private BusinessSucCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public static BusinessSucCode getByCode(String code) {
        BusinessSucCode[] arr$ = values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            BusinessSucCode enums = arr$[i$];
            if (enums.getCode().equals(code)) {
                return enums;
            }
        }

        return null;
    }
}
