package com.banana.event.demo.export.service.order.param;

import com.banana.event.demo.export.request.AbstractCmdRequest;
import lombok.Data;

/**
 * 订单提交参数
 * @author: bb
 * @date: 2023/7/7 11:29 上午
 * @version: 1.0
 */
@Data
public class SubmitOrderParam extends AbstractCmdRequest {

    private String productIds;

    private String userId;
}
