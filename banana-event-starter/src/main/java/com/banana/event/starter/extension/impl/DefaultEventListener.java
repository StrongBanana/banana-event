package com.banana.event.starter.extension.impl;

import com.banana.event.starter.base.Event;
import com.banana.event.starter.base.EventConsumerTask;
import com.banana.event.starter.extension.EventListener;
import lombok.extern.slf4j.Slf4j;

/**
 */
@Slf4j
public class DefaultEventListener implements EventListener {


    /**
     * 事件处理失败默认日志打印
     * @param event 需要报警的事件
     * @param task 事件消处理器
     * @param e 处理是不的异常
     */
    @Override
    public void consumeWarning(Event event, EventConsumerTask task, Exception e) {
        log.error("event consumeWarning eventid=" +event.getEventId() +";consumerCode="+task.getConsumerCode()
                + ";errmsg=" + e.getMessage());
    }

    /**
     * 事件发布失败默认打印日志
     * @param event
     * @param e
     */
    @Override
    public void publishWarning(Event event, Exception e) {
        log.error("event consumeWarning eventCode=" +event.getEventCode() +";aggregateId="+event.getAggregateId()
                + ";errmsg=" + e.getMessage());
    }
}
