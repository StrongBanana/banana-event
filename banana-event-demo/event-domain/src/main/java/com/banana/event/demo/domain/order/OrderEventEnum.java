package com.banana.event.demo.domain.order;

import com.banana.event.starter.base.AggregateType;
import com.banana.event.starter.base.EventType;

/**
 * 订单领域事件
 *
 */
public enum OrderEventEnum implements EventType {

    /** 套餐事件 */
    SUBMIT_ORDER_SUCCESS(OrderAggregateTypeEnum.ORDER,  "submitOrderSuccess","提单成功"),
    SUBMIT_ORDER_FAIL(OrderAggregateTypeEnum.ORDER,  "submitOrderFail","提单失败"),
    ;

    /**
     * constructor
     */
    OrderEventEnum(AggregateType aggregateType, String code, String desc) {
        this.aggregateType = aggregateType;
        this.code = code;
        this.desc = desc;
    }
    /** 事件所属领域，用于区分 */
    private AggregateType aggregateType;
    /** 事件名称 */
    private String code;
    /** 事件说明 */
    private String desc;
    /** */
    @Override
    public AggregateType getAggregateType() {
        return aggregateType;
    }
    /** */
    @Override
    public String getCode() {
        return code;
    }
    /** */
    @Override
    public String getDesc() {
        return desc;
    }
}
