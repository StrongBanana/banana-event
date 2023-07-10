package com.banana.event.demo.domain.order.aggregate;

import com.banana.event.starter.base.Identify;

/**
 * @author: bb
 * @date: 2023/7/7 12:06 下午
 * @version: 1.0
 */
public class Order implements Identify<Long>{


    private Long orderId;

    @Override
    public Long getUniqueId() {
        return orderId;
    }

    /**
     * 提单成功
     * @return
     */
    public Boolean submitSuccess(){

        return Boolean.TRUE;
    }
}
