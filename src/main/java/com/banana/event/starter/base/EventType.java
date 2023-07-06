package com.banana.event.starter.base;


/**
 * 事件类型描述 接口
 * @author: banana
 * @date: 2022/9/5 9:35 上午
 * @version: 1.0
 */
public interface EventType {

    /** 事件所属的领域 */
    AggregateType getAggregateType();
    /** 事件编码 */
    String getCode();
    /** 事件描述 */
    String getDesc();
}
