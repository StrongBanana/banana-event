package com.banana.event.starter.extension;

import com.banana.event.starter.base.EventConsumerTask;

import java.util.List;

/**
 * 事件消费者 仓储 任务扩展点
 * @author: banana
 * @date: 2022/8/22 11:32 上午
 * @version: 1.0
 */
public interface ConsumerTaskRepository {

    /**
     * 保存事件
     * @param tasks
     */
    void batchCreate(List<EventConsumerTask> tasks);

    /**
     * 刷新任务执行状态
     * @param task 事件消费者任务
     */
    void refreshState(EventConsumerTask task);

    /**
     *
     * @param eventId
     * @param consumerCode
     * @return
     */
    EventConsumerTask getTask(Long eventId, String consumerCode);




}
