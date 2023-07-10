package com.banana.event.starter;

import com.banana.event.starter.base.Event;

import java.util.function.Consumer;

/**
 * 事件消费者接口
 * @author: banana
 * @date: 2022/8/10 6:36 下午
 * @version: 1.0
 */
public interface EventConsumer extends Consumer<Event> {

    /**
     * 接受一个事件 并处理
     * @param t
     */
    @Override
    void accept(Event t);
}