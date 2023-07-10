package com.banana.event.demo.export.response;

/**
 * 抽象返回结果
 */
public abstract class AbstractResponse {
    /** 调用成功码 */
    public static final String SUCCESS = "0000";
    /** */
    private String code;
    /** */
    private String msg;
    /** */
    public String getCode() {
        return this.code;
    }
    /** */
    public void setCode(String code) {
        this.code = code;
    }
    /** */
    public String getMsg() {
        return this.msg;
    }
    /** */
    public void setMsg(String msg) {
        this.msg = msg;
    }
    /** */
    public boolean isSuccess() {
        return SUCCESS.equals(this.code);
    }
}
