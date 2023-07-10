package com.banana.event.starter.factory;

import com.banana.event.starter.EventStarter;
import com.banana.event.starter.base.Event;
import com.banana.event.starter.base.EventType;
import com.banana.event.starter.base.Identify;
import com.banana.event.starter.extension.EventIdFactory;

/**
 * 事件工厂，扩展点接口EventIdFactory
 * @author: banana
 * @date: 2022/8/10 6:47 下午
 * @version: 1.0
 */
public class EventFactory {

    /**
     * 新建一个通用事件
     * @param identify 触发事件的聚合根标识（Identify是为你标识，可以扩展）
     * @param eventType
     * @return
     */
    public static Event newDefaultEvent(Identify identify, EventType eventType){
        EventIdFactory generateIdService = EventStarter.getBean(EventIdFactory.class);
        Long id = generateIdService.getEventId();
        return new Event(identify, eventType, id);
    }
}
