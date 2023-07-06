package com.banana.event.starter.extension;

import com.banana.event.starter.base.Event;

/**
 * 事件仓储扩展点
 * @author: banana
 * @date: 2022/8/22 11:32 上午
 * @version: 1.0
 */
public interface EventRepository {

    /**
     * 保存事件
     * @param event
     */
    void create(Event event);

    /**
     * 刷新事件分值
     */
    Integer subtractScore(Long eventId);

    /**
     * 查询事件
     */
    Event getEvent(Long eventId);
}
