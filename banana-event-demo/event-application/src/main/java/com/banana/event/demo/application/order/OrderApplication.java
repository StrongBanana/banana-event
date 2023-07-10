package com.banana.event.demo.application.order;

import com.banana.event.demo.application.BusinessCmdContext;
import com.banana.event.demo.application.order.cmd.SubmitOrderCmd;
import com.banana.event.demo.domain.order.OrderEventEnum;
import com.banana.event.demo.domain.order.aggregate.Order;
import com.banana.event.starter.EventCoordinator;
import com.banana.event.starter.base.Event;
import com.banana.event.starter.factory.EventFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: bb
 * @date: 2023/7/7 11:45 上午
 * @version: 1.0
 */
@Component
public class OrderApplication {

    @Resource
    private EventCoordinator eventCoordinator;
    private Object Order;

    public Boolean submitOrder(BusinessCmdContext<SubmitOrderCmd> orderCmd){

         // 执行提单能力
        Order order = new Order();
        // 执行业务逻辑

            // 发布事件
        if (order.submitSuccess()){
            Event event = EventFactory.newDefaultEvent(order, OrderEventEnum.SUBMIT_ORDER_SUCCESS);
            eventCoordinator.publish(event);
        } else {
            //套餐下架
            Event event = EventFactory.newDefaultEvent(order, OrderEventEnum.SUBMIT_ORDER_FAIL);
            eventCoordinator.publish(event);
        }
        return Boolean.TRUE;

    }
}
