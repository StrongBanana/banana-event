package com.banana.event.starter.base;

import lombok.Builder;
import lombok.Data;

/**
 * 事件消费处理任务
 * @author: banana
 * @date: 2022/8/22 6:28 下午
 * @version: 1.0
 */
@Data
@Builder
public class EventConsumerTask {
    /** 任务处理成功 */
    public static final Integer SUCCESS = 1;
    /** 任务初始化 */
    public static final Integer INIT = 0;
    /** 任务执行失败 */
    public static final Integer FAIL = -1;

    /** 领域编号 */
    private String domainCode;
    /** 事件类型编号 */
    private String eventCode;
    /** 事件ID */
    private Long eventId;
    /** 消费者编号 */
    private String consumerCode;
    /** */
    private Integer status;
    /** true:异步消费 */
    private Boolean isAsync;
    /** 消费次数 */
    private Integer num;


    /**
     * 处理成功，执行次数加1
     */
    public void processSuccess(){
        this.status = SUCCESS;
        this.num++;
    }
    /**
     * 处理失败，执行次数加1
     */
    public void processFail(){
        this.status = FAIL;
        this.num++;
    }
}
