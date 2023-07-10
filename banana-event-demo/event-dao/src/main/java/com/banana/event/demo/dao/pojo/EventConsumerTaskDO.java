package com.banana.event.demo.dao.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 事件任务持久化
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventConsumerTaskDO {
    /**
    * 主键
    */
    private Long id;

    /**
    * 事件消费者所属领域标识
    */
    private String domainCode;

    /**
    * 事件类型
    */
    private String eventCode;

    /**
    * 事件ID
    */
    private Long eventId;

    /**
    * 处理器编号
    */
    private String consumerCode;

    /**
    * 处理结果-1:处理失败，0:待消费，1:处理成功，2:失效
    */
    private Integer status;

    /**
    * 执行次数
    */
    private Integer num;

    /**
     * 异步执行（1：异步，0：同步）
     */
    private Integer async;

    /**
    * 逻辑状态
    */
    private Integer yn;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;
}