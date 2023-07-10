package com.banana.event.starter.factory;

import com.banana.event.starter.EventConsumerRegister;
import com.banana.event.starter.WrapperEventConsumer;
import com.banana.event.starter.base.Event;
import com.banana.event.starter.base.EventConsumerTask;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 事件消费日志
 * @author: banana
 * @date: 2022/8/22 3:42 下午
 * @version: 1.0
 */
public class EventConsumerLogFactory {



    /**
     * 根据注册到事件的处理器，创建事件处理任务
     * @param event 触发事件的聚合根标识（Identify是为你标识，可以扩展）
     * @return
     */
    public static List<EventConsumerTask> buildTasks(Event event){
        List<WrapperEventConsumer> list = EventConsumerRegister.consumers(event);
        List<EventConsumerTask> res = Lists.newArrayListWithExpectedSize(list.size());
        for (WrapperEventConsumer consumer : list) {
            EventConsumerTask task = EventConsumerTask.builder()
                    .consumerCode(consumer.getConsumerCode())
                    .eventId(event.getEventId())
                    .eventCode(event.getEventCode())
                    .domainCode(consumer.getDomain().getCode())
                    .status(EventConsumerTask.INIT)
                    .isAsync(consumer.getAsync())
                    .num(0)
                    .build();
            res.add(task);
        }

        return res;
    }
}
