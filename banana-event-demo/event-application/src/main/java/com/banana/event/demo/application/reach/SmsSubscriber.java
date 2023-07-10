package com.banana.event.demo.application.reach;

import com.banana.event.demo.domain.DomainEnum;
import com.banana.event.demo.domain.order.OrderEventEnum;
import com.banana.event.starter.EventRegister;
import com.banana.event.starter.WrapperEventConsumer;
import com.banana.event.starter.base.Event;
import org.springframework.stereotype.Component;
import com.banana.event.demo.domain.reach.service.SmsTriggerService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author: bb
 * @date: 2023/7/10 10:21 上午
 * @version: 1.0
 */
@Component
public class SmsSubscriber {

    @Resource
    private EventRegister register;
    @Resource
    private SmsTriggerService smsTriggerService;
    @Resource
    private SmsSubscriber smsSubscriber;

    @PostConstruct
    public void init(){
        registerOrderEventConsumer();
    }


    /**
     * 注册套餐领域，套餐聚合触发的事件
     */
    public void registerOrderEventConsumer(){
        // 服务领域处理套餐下架（同步消费事件）
        WrapperEventConsumer submitSuccess = WrapperEventConsumer.newInstance(DomainEnum.REACH, "orderSubmitSuccessSmsTrigger", smsSubscriber::sendOrderSubmitSuccessSms, Boolean.FALSE);
        register.register(OrderEventEnum.SUBMIT_ORDER_SUCCESS, submitSuccess);
    }

    /**
     * 处理提单成功的短信发生
     * @param event
     */
    public void sendOrderSubmitSuccessSms(Event event){

    }

}
