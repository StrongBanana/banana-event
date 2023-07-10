package com.banana.event.demo.dao.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 事件持久化记录
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRecord {
    /**
    * 主键
    */
    private Long id;

    /**
    * 事件ID
    */
    private Long eventId;

    /**
    * 领域名称
    */
    private String domainCode;
    /** 聚合类型 */
    private String aggregateCode;

    /** 聚合对象唯一标识 */
    private String aggregateId;

    /**
    * 版本号，这个版本号是基于domain_id发布的事件版本号
    */
    private Integer version;

    /**
    * 事件名称
    */
    private String eventCode;

    /**
    * 事件发布时间
    */
    private Date publishTime;

    /**
    * 事件主体内容
    */
    private String body;

    /**
    * 事件消费者集合
    */
    private String consumerIds;
    /** */
    private Integer score;

    /**
    * 逻辑状态
    */
    private Integer yn;

    /**
    * 创建时间
    */
    private Date createTime;
    /** */
    private Date updateTime;
}