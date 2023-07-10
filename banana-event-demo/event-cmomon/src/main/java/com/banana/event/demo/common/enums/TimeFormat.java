package com.banana.event.demo.common.enums;

import java.time.format.DateTimeFormatter;

/**
 *
 */
public enum TimeFormat {

    /**
     * 短时间格式
     */
    SHORT_PATTERN_LINE("yyyy-MM-dd"),
    SHORT_PATTERN_SLASH("yyyy/MM/dd"),
    SHORT_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd"),
    SHORT_PATTERN_NONE("yyyyMMdd"),
    SHORT_PATTERN_CHINA("yyyy年MM月dd日"),
    SHORT_PATTERN_DOT("yyyy.MM.dd"),

    /**
     * 长时间格式
     */
    LONG_PATTERN_LINE("yyyy-MM-dd HH:mm:ss"),
    LONG_PATTERN_LINE_NO_S("yyyy-MM-dd HH:mm"),
    LONG_PATTERN_DOT_NO_S("yyyy.MM.dd HH:mm"),
    LONG_PATTERN("yyyy.MM.dd HH:mm:ss"),
    LONG_PATTERN_SLASH("yyyy/MM/dd HH:mm:ss"),
    LONG_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss"),
    LONG_PATTERN_NONE("yyyyMMdd HH:mm:ss"),
    LONG_PATTERN_NONE_COLON("yyyyMMddHHmmss"),
    LONG_PATTERN_CHINA("yyyy年MM月dd日 HH时mm分ss秒"),

    /**
     * 长时间格式 带毫秒
     */
    LONG_PATTERN_WITH_MILSEC_LINE("yyyy-MM-dd HH:mm:ss.SSS"),
    LONG_PATTERN_WITH_MILSEC_SLASH("yyyy/MM/dd HH:mm:ss.SSS"),
    LONG_PATTERN_WITH_MILSEC_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss.SSS"),
    LONG_PATTERN_WITH_MILSEC_NONE("yyyyMMdd HH:mm:ss.SSS"),

    /**
     * 非标准时间格式
     */
    DATE_PATTERN_HMS_SIMPLE("HH:mm:ss"),
    DATE_PATTERN_HM_SIMPLE("HH:mm"),
    DATE_PATTERN_HMS_NONE("HHmmss"),

    DATE_PATTERN_MD_SIMPLE("MM-dd"),
    DATE_PATTERN_MD_NONE("MMdd"),
    DATE_PATTERN_MD_SPOT("MM.dd"),

    DATE_PATTERN_YYYYMM_SIMPLE("yyyy-MM");
    /**
     * 无状态的，可以线程共享
     */
    public transient DateTimeFormatter formatter;

    /**
     * pattern
     */
    public String pattern;

    /**
     * 有参构造方法
     * @param pattern
     */
    TimeFormat(String pattern) {
        formatter = DateTimeFormatter.ofPattern(pattern);
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}