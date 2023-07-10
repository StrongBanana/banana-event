package com.banana.event.demo.domain;

import com.banana.event.starter.base.EventDomain;

/**
 * 领域枚举
 */
public enum DomainEnum implements EventDomain {

    /** 门店领域 */
    ORDER("order",1,"订单领域"),
    REACH("reach",2,"触达领域"),
    ;
    /**
     * constructor
     */
    DomainEnum(String code, Integer index, String name) {
        this.code = code;
        this.name = name;
        this.index = index;
    }

    /** */
    private String code;
    /** */
    private Integer index;
    /** */
    private String name;

    /** */
    @Override
    public String getName() {
        return name;
    }
    /** */
    @Override
    public String getCode() {
        return code;
    }
    /** */
    public Integer getIndex() {
        return index;
    }
}
