package com.banana.event.starter.base;

/**
 * 领域接口，基于DDD的领域标识接口
 * @date 2023-07-05 18:03
 * @author banana
 */
public interface EventDomain {

    /**
     * 领域编码
     * @return
     */
    String getCode();

    /**
     * 名称
     * @return
     */
    String getName();
}
