package com.banana.event.demo.application;

import lombok.Data;

/**
 *
 */
@Data
public class BusinessCmdContext<T> {


    private T cmd;

    private Integer bizCode;

    private Integer bizType;
}
