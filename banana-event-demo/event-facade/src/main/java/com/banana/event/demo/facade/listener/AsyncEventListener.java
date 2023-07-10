package com.banana.event.demo.facade.listener;

import com.banana.event.starter.EventCoordinator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 异步事件监听处理器（基于MQ或者定时任务，读取数据库中的异步事件进行处理）
 * @date: 2023/7/10 11:32 上午
 * @version: 1.0
 */
@Component
public class AsyncEventListener {


    @Resource
    private EventCoordinator eventCoordinator;



    public void process(){
        Long eventId = 1000L;
        String consumerCode = "ada";
        eventCoordinator.disposeAsyncEvent(eventId, consumerCode);
    }

}
