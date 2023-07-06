package com.banana.event.starter.extension.impl;

import com.banana.event.starter.extension.EventIdFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
/**
 * 默认的扩展点能力
 * @author: yangxiyu
 * @date: 2023/7/5 6:25 下午
 * @version: 1.0
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
