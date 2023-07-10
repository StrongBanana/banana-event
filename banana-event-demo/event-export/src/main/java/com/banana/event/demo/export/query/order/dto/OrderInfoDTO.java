package com.banana.event.demo.export.query.order.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 */
@Data
public class OrderInfoDTO {

    private Long orderId;

    private BigDecimal price;
}
