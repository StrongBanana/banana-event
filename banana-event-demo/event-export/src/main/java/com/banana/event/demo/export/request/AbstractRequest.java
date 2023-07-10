package com.banana.event.demo.export.request;

/**
 * 抽象请求参数
 */
public abstract class AbstractRequest {

    /** 客户端名称，不可为空 */
    private String appName;
    /** 客户端ip，不可为空 */
    private String clientIp;
    /** 授权的key */
    private String accessKey;
    /** (垂直)业务身份 */
    private Integer bizCode;
    /** (水平业务身份) */
    private Integer bizType;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public Integer getBizCode() {
        return bizCode;
    }

    public void setBizCode(Integer bizCode) {
        this.bizCode = bizCode;
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }
}
