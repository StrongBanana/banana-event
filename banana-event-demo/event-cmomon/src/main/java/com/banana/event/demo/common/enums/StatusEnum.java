package com.banana.event.demo.common.enums;

/**
 *
 */
public enum StatusEnum {
    /** 有效 **/
    YES(1,"有效"),
    /** 失效 **/
    NO(0,"失效");

    /**
     * 有参构造器
     * @param code
     * @param desc
     */
    StatusEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 描述
     */
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
