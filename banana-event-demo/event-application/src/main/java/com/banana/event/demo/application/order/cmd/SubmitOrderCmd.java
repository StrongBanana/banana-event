package com.banana.event.demo.application.order.cmd;

import com.banana.event.demo.application.BusinessCmdContext;
import lombok.Data;

/**
 * @author: bb
 * @date: 2023/7/7 11:46 上午
 * @version: 1.0
 */
@Data
public class SubmitOrderCmd extends BusinessCmdContext {

    private String productIds;

    private String userId;
}
