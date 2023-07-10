package com.banana.event.demo.dao.mapper;

import com.banana.event.demo.dao.pojo.EventRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 消费医疗事件记录DAO
 */
public interface EventRecordDao {
    /** */
    int insert(EventRecord record);
    /** */
    int subtractScore(@Param("eventId") Long eventId);

    /** */
    EventRecord selectById(@Param("eventId") Long eventId);
}