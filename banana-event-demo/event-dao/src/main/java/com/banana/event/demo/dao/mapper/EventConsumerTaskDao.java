package com.banana.event.demo.dao.mapper;

import com.banana.event.demo.dao.pojo.EventConsumerTaskDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 */
public interface EventConsumerTaskDao {


    /**
     *
     * @param records
     * @return
     */
    int batchInsert(List<EventConsumerTaskDO> records);

    /**
     *
     * @return
     */
    int refreshState(@Param("eventId")Long eventId,
                     @Param("consumerCode")String consumerCode,
                     @Param("status")Integer status,
                     @Param("num")Integer num);

    /**
     * 根据事件编号、处理器编号获取消费者
     * @param eventId
     * @param consumerCode
     * @return
     */
    EventConsumerTaskDO selectByEventId(@Param("eventId") Long eventId,
                                            @Param("consumerCode") String consumerCode);
}
