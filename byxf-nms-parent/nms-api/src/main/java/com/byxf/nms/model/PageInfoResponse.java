package com.byxf.nms.model;

import java.io.Serializable;
import java.util.List;

public class PageInfoResponse<T> implements Serializable {

    private int pageNo;

    private int pageSize;

    private int totlePages;

    private long totleSize;

    private List<T> object;

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

    public List<T> getObject() {
        return object;
    }

    public void setObject(List<T> object) {
        this.object = object;
    }

    public int getTotlePages() {
        return totlePages;
    }

    public void setTotlePages(int totlePages) {
        this.totlePages = totlePages;
    }

    public long getTotleSize() {
        return totleSize;
    }

    public void setTotleSize(long totleSize) {
        this.totleSize = totleSize;
    }

    public PageInfoResponse() {
    }

    public PageInfoResponse(int pageNo, int pageSize, int totlePages, long totleSize, List<T> object) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totlePages = totlePages;
        this.totleSize = totleSize;
        this.object = object;
    }
}
