package com.banana.event.starter;

import com.banana.event.starter.base.Event;
import com.banana.event.starter.base.EventConsumerTask;
import com.banana.event.starter.base.EventDomain;
import com.banana.event.starter.extension.ConsumerTaskRepository;
import com.banana.event.starter.extension.EventWarning;
import com.banana.event.starter.extension.EventRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * 事件消费者包装类
 * @author: banana
 * @date: 2022/8/10 6:54 下午
 * @version: 1.0
 */
@Slf4j
public class WrapperEventConsumer implements EventConsumer {

    /** 处理器所属的领域 */
    private EventDomain domain;
    /** 处理器编号 */
    private String consumerCode;
    /**  */
    private EventConsumer consumer;

    /**
     * 异步消费、同步消费
     */
    private Boolean isAsync;
    /** */
    public String getConsumerCode() {
        return consumerCode;
    }
    /** */
    public EventConsumer getConsumer() {
        return consumer;
    }
    /** */
    public Boolean getAsync() {
        return isAsync;
    }
    /** */
    public EventDomain getDomain() {
        return domain;
    }
    /** 私有化构造 */
    private WrapperEventConsumer() {
    }

    /**
     * 创建一个事件处理器实例
     * @param domain 处理器所属领域
     * @param consumerCode 处理器编号
     * @param consumer 处理器
     * @param isAsync 同步异步
     * @return
     */
    public static WrapperEventConsumer newInstance(EventDomain domain, String consumerCode, EventConsumer consumer, Boolean isAsync){
        WrapperEventConsumer wrapper = new WrapperEventConsumer();
        wrapper.domain = domain;
        wrapper.consumerCode = consumerCode;
        wrapper.consumer = consumer;
        wrapper.isAsync = isAsync;
        return wrapper;
    }

    /**
     * 异步执行任务
     * @param t
     */
    @Override
    public void accept(Event t) {
        log.info("WrapperEventConsumer->accept eventId={}, consumerCode={}", t.getEventId(), consumerCode);
        ConsumerTaskRepository eventConsumerTaskRepository = EventStarter.getBean(ConsumerTaskRepository.class);
        EventRepository eventRepository = EventStarter.getBean(EventRepository.class);
        EventConsumerTask task = eventConsumerTaskRepository.getTask(t.getEventId(), consumerCode);
        try{
            consumer.accept(t);
            task.processSuccess();
            t.subtractScore();
            eventRepository.subtractScore(t.getEventId());
        }catch (Exception e){
            log.error("WrapperEventConsumer->accept error", e);
            EventWarning listenerExtension = EventStarter.getBean(EventWarning.class);
            listenerExtension.consumeWarning(t, task, e);
            task.processFail();
            // 同步事件处理器，抛出异常
            if (!isAsync){
                throw e;
            }
        }finally {
            // 保存任务
            eventConsumerTaskRepository.refreshState(task);
        }
    }

    /**
     * 同步处理器处理事件，处理成功后事件需要减去分值
     * 处理结束有
     * @param t
     */
    public void acceptSync(Event t, EventConsumerTask task) {
        consumer.accept(t);
        task.processSuccess();
        t.subtractScore();
    }
}
