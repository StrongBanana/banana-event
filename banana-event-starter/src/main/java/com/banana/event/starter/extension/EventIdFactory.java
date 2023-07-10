package com.banana.event.starter.extension;

/**
 * 事件ID 工厂
 * @author: banana
 * @date: 2022/8/17 10:00 下午
 * @version: 1.0
 */
public interface EventIdFactory {

    /**
     * 获取一个事件唯一编号
     * @return
     */
    Long getEventId();
}
