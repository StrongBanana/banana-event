package com.banana.event.starter.extension.impl;

import com.banana.event.starter.extension.EventIdFactory;
/**
 * 默认的扩展点能力
 */
public class DefaultEventIdFactory implements EventIdFactory {

    /**
     * 获取
     * @return
     */
    @Override
    public Long getEventId() {
        return System.currentTimeMillis();
    }
}
