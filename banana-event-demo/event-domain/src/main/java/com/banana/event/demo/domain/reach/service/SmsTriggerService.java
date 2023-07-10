package com.banana.event.demo.domain.reach.service;


import com.banana.event.starter.base.Event;

/**
 * @author: bb
 * @date: 2023/7/10 10:24 上午
 * @version: 1.0
 */
public interface SmsTriggerService {


    void sendOrderSubmitSuccessSms(Event event);
}
