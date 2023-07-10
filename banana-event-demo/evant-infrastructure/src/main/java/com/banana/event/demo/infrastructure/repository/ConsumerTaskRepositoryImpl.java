package com.banana.event.demo.infrastructure.repository;

import cn.hutool.core.collection.CollectionUtil;
import com.banana.event.demo.common.enums.StatusEnum;
import com.banana.event.demo.dao.mapper.EventConsumerTaskDao;
import com.banana.event.demo.dao.pojo.EventConsumerTaskDO;
import com.banana.event.starter.base.EventConsumerTask;
import com.banana.event.starter.extension.ConsumerTaskRepository;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 实现ConsumerTaskRepository，存储
 */
@Component
public class ConsumerTaskRepositoryImpl implements ConsumerTaskRepository {

    @Resource
    private EventConsumerTaskDao eventConsumerTaskDao;

    public void batchCreate(List<EventConsumerTask> tasks) {
        if (CollectionUtil.isEmpty(tasks)){
            return;
        }
        List<EventConsumerTaskDO> records = Lists.newArrayListWithExpectedSize(tasks.size());
        for (EventConsumerTask task : tasks) {
            EventConsumerTaskDO taskDO = EventConsumerTaskDO.builder()
                    .domainCode(task.getDomainCode())
                    .eventCode(task.getEventCode())
                    .eventId(task.getEventId())
                    .consumerCode(task.getConsumerCode())
                    .status(task.getStatus())
                    .num(task.getNum())
                    .async(task.getIsAsync() ? StatusEnum.YES.getCode() : StatusEnum.NO.getCode())
                    .build();
            records.add(taskDO);
        }
        eventConsumerTaskDao.batchInsert(records);
    }

    public void refreshState(EventConsumerTask task) {
        eventConsumerTaskDao.refreshState(task.getEventId(), task.getConsumerCode(), task.getStatus(), task.getNum());

    }

    public EventConsumerTask getTask(Long eventId, String consumerCode) {
        EventConsumerTaskDO task = eventConsumerTaskDao.selectByEventId(eventId, consumerCode);

        return EventConsumerTask.builder()
                .domainCode(task.getDomainCode())
                .consumerCode(task.getConsumerCode())
                .eventCode(task.getEventCode())
                .eventId(task.getEventId())
                .num(task.getNum())
                .isAsync(convertAsync(task.getAsync()))
                .status(task.getStatus())
                .build();
    }

    /**
     *
     * @param value
     * @return
     */
    private Boolean convertAsync(Integer value){
        if (Objects.equals(value, StatusEnum.YES.getCode())){
            return Boolean.TRUE;
        }
        if (Objects.equals(value, StatusEnum.NO.getCode())){
            return Boolean.FALSE;
        }
        throw new IllegalArgumentException("value illegal");
    }
}
