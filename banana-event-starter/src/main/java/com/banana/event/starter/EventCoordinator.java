package com.banana.event.starter;

import com.banana.event.starter.base.Event;
import com.banana.event.starter.base.EventConsumerTask;
import com.banana.event.starter.base.EventException;
import com.banana.event.starter.extension.ConsumerTaskRepository;
import com.banana.event.starter.extension.EventWarning;
import com.banana.event.starter.extension.EventRepository;
import com.banana.event.starter.factory.EventConsumerLogFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 事件协调处理器
 * @author: banana
 * @date: 2022/8/17 10:26 下午
 * @version: 1.0
 */
@Slf4j
public class EventCoordinator {

    /** */
    @Resource
    private EventRepository eventRepository;
    /** */
    @Resource
    private ConsumerTaskRepository consumerTaskRepository;
    /** */
    @Resource
    private EventWarning eventWarning;

    /**
     * 发布事件
     * 1、校验事件数据完整性；
     * 2、使用同步的事件处理器，处理事件；
     * 3、处理结束后，将事件序列化到 mysql(用于异步处理事件)
     * @param event
     */
    public void publish(Event event){
        log.info("EventCoordinator->publish event={}", event);
        try {
            // 构建同步事件消费者处理日志
            List<EventConsumerTask> tasks = EventConsumerLogFactory.buildTasks(event);
            for (EventConsumerTask task : tasks) {
                if (!task.getIsAsync()){
                    WrapperEventConsumer wrapperEventConsumer = EventConsumerRegister.syncConsumer(event, task.getConsumerCode());
                    wrapperEventConsumer.acceptSync(event, task);
                }
            }
            // 事件持久化，任务持久化
            eventRepository.create(event);
            consumerTaskRepository.batchCreate(tasks);
        }catch (Exception e){
            log.error("EventCoordinator->publish error", e);
            eventWarning.publishWarning(event, e);
            throw new EventException("事件发布失败" + e.getMessage());
        }
    }





    /**
     * 处理异步事件
     * 1、获取当前事件注册的异步处理器；
     * 2、判断这个事件是否已经处理过；
     * 3、开启异步执行，执行成功需要将记录添加到EVENT_ID_HISTORY，缓存7天
     * @param eventId
     * @param consumerCode
     */
    public void disposeAsyncEvent(Long eventId, String consumerCode) {
        log.info("EventCoordinator->disposeAsyncEvent eventId={}，consumerCode={}", eventId, consumerCode);
        if (Objects.isNull(eventId)){
            throw new IllegalArgumentException("eventId is require");
        }

        if (StringUtils.isBlank(consumerCode)){
            throw new IllegalArgumentException("consumerCode is require");
        }
        Event existEvent = eventRepository.getEvent(eventId);
        if (Objects.isNull(existEvent)){
            throw new IllegalArgumentException("event not exist");
        }
        WrapperEventConsumer wrapper = EventConsumerRegister.getConsumer(existEvent, consumerCode);
        // todo 提供配置，如果可以容忍wrapper不存在的情况，则不处理
        if (Objects.isNull(wrapper)){
            throw new EventException("unwatch consumer consumerCode=" + consumerCode );
        }
        if (!wrapper.getAsync()){
            log.info("EventCoordinator->disposeAsyncEvent wrapper is sync break");
            return;
        }

        wrapper.accept(existEvent);
    }


}
