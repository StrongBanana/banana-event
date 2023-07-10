package com.banana.event.demo.domain.order;

import com.banana.event.demo.domain.DomainEnum;
import com.banana.event.starter.base.AggregateType;
import com.banana.event.starter.base.EventDomain;

/**
 * 订单领域聚合类型
 */
public enum OrderAggregateTypeEnum implements AggregateType {
    /** */
    ORDER(DomainEnum.ORDER, "order"),
    ;

    /** */
    private EventDomain domain;
    /** */
    private String code;

    /** */
    OrderAggregateTypeEnum(EventDomain domain, String code) {
        this.domain = domain;
        this.code = code;
    }
    /** */
    @Override
    public EventDomain getDomain() {
        return domain;
    }
    /** */
    @Override
    public String getCode() {
        return code;
    }
}
