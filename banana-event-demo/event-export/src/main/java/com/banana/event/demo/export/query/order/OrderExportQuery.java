package com.banana.event.demo.export.query.order;

import com.banana.event.demo.export.query.order.dto.OrderInfoDTO;
import com.banana.event.demo.export.query.order.param.OrderIdParam;

/**
 *
 */
public interface OrderExportQuery {


    OrderInfoDTO queryOrder(OrderIdParam orderIdParam);
}
