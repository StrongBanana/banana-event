package com.banana.event.starter.base;

import com.alibaba.fastjson.JSON;
import com.banana.event.starter.EventConsumerRegister;

import java.time.LocalDateTime;

/**
 * 消费医疗事件
 * @author: banana
 * @date: 2022/8/10 6:30 下午
 * @version: 1.0
 */
public class Event {

    /** 事件所属领域 */
    private String domainCode;
    /** 事件所属聚合 */
    private String aggregateCode;
    /** 事件主体 */
    private String aggregateId;
    /** 事件类型编号 */
    private String eventCode;
    /** 事件ID */
    private Long eventId;
    /** 发布时间 */
    private LocalDateTime publishTime;

    /** 事件的分值，每个关注当前事件的处理器会为事件加一分 */
    private Integer score;
    /** 事件处理器编号集合 */
    private String consumerCodes;

    /** constructor empty */
    public Event() {
    }
    /** constructor all filed*/
    public Event(Identify body, EventType eventType, Long eventId) {
        this.domainCode = eventType.getAggregateType().getDomain().getCode();
        this.aggregateCode = eventType.getAggregateType().getCode();
        if (body.getUniqueId() == null){
            throw new NullPointerException("aggregateId is null");
        }

        if (body.getUniqueId() instanceof String){
            this.aggregateId = (String) body.getUniqueId();
        }else{
            this.aggregateId = JSON.toJSONString(body.getUniqueId());
        }
        this.eventCode = eventType.getCode();
        this.eventId = eventId;
        this.publishTime = LocalDateTime.now();

        // 获取当前事件注册的consumer，每个consumer 1分
        Integer score = EventConsumerRegister.getScore(eventType);
        this.score = (score == null ? 0 : score);
        this.consumerCodes = EventConsumerRegister.getConsumerCodes(eventType);
    }

    /**
     * 事件唯一ID
     */
    public Long getEventId() {
        return eventId;
    }

    /** */
    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    /** */
    public Integer getScore() {
        return score;
    }
    /** */
    public String getConsumerCodes() {
        return consumerCodes;
    }
    /** */
    public String getAggregateCode() {
        return aggregateCode;
    }
    /** */
    public void setAggregateCode(String aggregateCode) {
        this.aggregateCode = aggregateCode;
    }

    /** */
    public String getEventCode() {
        return eventCode;
    }
    /** */
    public String getDomainCode() {
        return domainCode;
    }
    /** */
    public void setDomainCode(String domainCode) {
        this.domainCode = domainCode;
    }
    /** */
    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }
    /** */
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }
    /** */
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
    /** */
    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }
    /** */
    public void setScore(Integer score) {
        this.score = score;
    }
    /** */
    public void setConsumerCodes(String consumerCodes) {
        this.consumerCodes = consumerCodes;
    }

    /**
     * 减去分值
     */
    public void subtractScore(){
        this.score = this.score--;
    }

    /**
     * 事件完结
     */
    public void finish(){
        this.score = 0;
    }

    /**
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public  <T> T parseAggregateId(Class<T> clazz){
        return JSON.parseObject(aggregateId, clazz);
    }
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Event{" +
                "domainCode='" + domainCode + '\'' +
                ", domainId='" + aggregateId + '\'' +
                ", eventCode='" + eventCode + '\'' +
                ", eventId=" + eventId +
                ", publishTime=" + publishTime +
                ", score=" + score +
                ", consumerCodes='" + consumerCodes + '\'' +
                '}';
    }
}
