package com.banana.event.demo.export.response;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询结果
 */
public class PageResponse<T> extends AbstractResponse implements Serializable {

    /** */
    private static final long serialVersionUID = 3281261508261282781L;
    /** */
    private Integer pageNum;
    /** */
    private Integer totalPage;
    /** */
    private Long totalCount;
    /** */
    private List<T> list;
    /** */
    public PageResponse() {
    }

    /** */
    public List<T> getList() {
        return this.list;
    }
    /** */
    public void setList(List<T> list) {
        this.list = list;
    }
    /** */
    public Integer getPageNum() {
        return pageNum;
    }
    /** */
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
    /** */
    public Integer getTotalPage() {
        return totalPage;
    }
    /** */
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
    /** */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    /** */
    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}
