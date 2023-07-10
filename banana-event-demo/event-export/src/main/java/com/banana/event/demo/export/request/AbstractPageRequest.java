package com.banana.event.demo.export.request;


/**
 * 客户端分页查询参数
 */
public abstract class AbstractPageRequest extends AbstractQueryRequest{

    /** */
    private int pageNum = 1;
    /** */
    private int pageSize = 10;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
