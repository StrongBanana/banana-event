package com.banana.event.demo.export.query.order.param;

import com.banana.event.demo.export.request.AbstractQueryRequest;
import lombok.Data;

/**
 * @author: bb
 * @date: 2023/7/7 11:34 上午
 * @version: 1.0
 */
@Data
public class OrderIdParam extends AbstractQueryRequest {

    private Long orderId;
}
