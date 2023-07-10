package com.banana.event.demo.export.request;

import java.util.Set;

/**
 * 抽象请求参数
 */
public abstract class AbstractQueryRequest extends AbstractRequest {
    /** 查询的结果要素 */
    private Set<String> elementCode;
    /** */
    public Set<String> getElementCode() {
        return elementCode;
    }
    /** */
    public void setElementCode(Set<String> elementCode) {
        this.elementCode = elementCode;
    }
}
