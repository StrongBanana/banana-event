package com.banana.event.starter.base;

/**
 * 聚合类型接口
 * @author: banana
 * @date 2023-07-05 18:02
 */
public interface AggregateType {

    /**
     * 聚合所属领域
     * @return
     */
    EventDomain getDomain();

    /**
     * 领域编号
     * @return
     */
    String getCode();
}
