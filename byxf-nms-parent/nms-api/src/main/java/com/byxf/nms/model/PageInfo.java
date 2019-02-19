package com.byxf.nms.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class PageInfo<T> {

    @ApiModelProperty(value = "页码")
    private int pageNo;
    @ApiModelProperty(value = "每页数")
    private int pageSize;

    private T object;

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

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public PageInfo() {
    }

    public PageInfo(int pageNo, int pageSize, T object) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.object = object;
    }
}
