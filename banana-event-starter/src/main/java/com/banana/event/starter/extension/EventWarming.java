package com.banana.event.starter.extension;

import com.banana.event.starter.base.Event;
import com.banana.event.starter.base.EventConsumerTask;

/**
 *
 * 1、项目启动后启动定时任务去调用（默认）；
 * 2、task的调用有用户自己控制
 * 事件监听器
 * @author: banana
 * @date: 2022/8/22 11:39 上午
 * @version: 1.0
 */
public interface EventWarming {


    /**
     * 事件消费失败 警告扩展点
     * @param event
     * @param task
     */
    void consumeWarning(Event event, EventConsumerTask task, Exception e);


    /**
     * 事件发布失败警告
     * @param event
     */
    void publishWarning(Event event, Exception e);


}
