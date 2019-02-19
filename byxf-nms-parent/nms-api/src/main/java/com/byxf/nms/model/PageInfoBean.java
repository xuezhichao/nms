package com.byxf.nms.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(value = "PageInfoBean")
public class PageInfoBean implements Serializable {

    @ApiModelProperty(value = "页码")
    private int pageNo;
    @ApiModelProperty(value = "每页数")
    private int pageSize;

    private NmsOsNewInfoBean nmsOsNewInfo;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public NmsOsNewInfoBean getNmsOsNewInfo() {
        return nmsOsNewInfo;
    }

    public void setNmsOsNewInfo(NmsOsNewInfoBean nmsOsNewInfo) {
        this.nmsOsNewInfo = nmsOsNewInfo;
    }

    public PageInfoBean() {
    }

    public PageInfoBean(int pageNo, int pageSize,NmsOsNewInfoBean nmsOsNewInfo) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.nmsOsNewInfo = nmsOsNewInfo;
    }
}
