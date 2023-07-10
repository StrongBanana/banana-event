package com.banana.event.demo.export.service.order;

import com.banana.event.demo.export.response.Response;
import com.banana.event.demo.export.service.order.param.SubmitOrderParam;

/**
 * @author: bb
 * @date: 2023/7/7 11:23 上午
 * @version: 1.0
 */
public interface OrderExportService {

    /**
     * 提交订单
     * @param param
     * @return
     */
    Response<Boolean> submitOrder(SubmitOrderParam param);
}
