package com.banana.event.demo.infrastructure.repository;

import com.banana.event.demo.common.utils.TimeUtils;
import com.banana.event.demo.dao.mapper.EventRecordDao;
import com.banana.event.demo.dao.pojo.EventRecord;
import com.banana.event.starter.base.Event;
import com.banana.event.starter.extension.EventRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 事件仓储实现
 */
@Component
public class EventRepositoryExtensionImpl implements EventRepository {

    /** */
    @Resource
    private EventRecordDao eventRecordDao;

    /** */
    @Override
    public  void create(Event event) {
        Date publishTime = TimeUtils.localDateTimeToDate(event.getPublishTime());
        EventRecord record = EventRecord.builder()
                .eventId(event.getEventId())
                .domainCode(event.getDomainCode())
                .aggregateCode(event.getAggregateCode())
                .aggregateId(event.getAggregateId())
                .eventCode(event.getEventCode())
                .publishTime(publishTime)
                .consumerIds(event.getConsumerCodes())
                .score(event.getScore())
                .build();
        eventRecordDao.insert(record);
    }

    /**
     * 刷新事件分值
     */
    @Override
    public Integer subtractScore(Long eventId) {
        return eventRecordDao.subtractScore(eventId);
    }


    /**
     * 查询事件
     */
    @Override
    public Event getEvent(Long eventId) {

        EventRecord eventRecord = eventRecordDao.selectById(eventId);
        LocalDateTime publishTime = TimeUtils.dateToLocalDateTime(eventRecord.getPublishTime());

        Event event = new Event();
        event.setDomainCode(eventRecord.getDomainCode());
        event.setAggregateCode(eventRecord.getAggregateCode());
        event.setAggregateId(eventRecord.getAggregateId());
        event.setEventCode(eventRecord.getEventCode());
        event.setEventId(eventRecord.getEventId());
        event.setPublishTime(publishTime);
        event.setConsumerCodes(eventRecord.getConsumerIds());
        event.setScore(eventRecord.getScore());
        return event;
    }

}
