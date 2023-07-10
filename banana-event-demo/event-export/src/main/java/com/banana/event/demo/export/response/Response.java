package com.banana.event.demo.export.response;

import java.io.Serializable;

/**
 * 通用返回结果
 */
public class Response<T> extends AbstractResponse implements Serializable {
    /** */
    private static final long serialVersionUID = 3281261508261282781L;
    /** */
    private T data;
    /** */
    public Response() {
    }
    /** */
    public T getData() {
        return this.data;
    }
    /** */
    public void setData(T data) {
        this.data = data;
    }
}
