package com.banana.event.demo.common.utils;

import com.banana.event.demo.common.enums.TimeFormat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * Note: 时间操作工具类
 * @author : lvyifan3
 * @date : 2020/6/16 14:30
 */
public class TimeUtils {

    /**
     * 私有构造方法
     */
    private TimeUtils() {

    }

    /**
     * 获取默认时间格式: yyyy-MM-dd HH:mm:ss
     */
    private static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = TimeFormat.LONG_PATTERN_LINE.formatter;

    /**
     * 获取默认时间格式: yyyy-MM-dd
     */
    private static final DateTimeFormatter DEFAULT_DATE_FORMATTER = TimeFormat.SHORT_PATTERN_LINE.formatter;

    /**
     * 获取当前时间
     * @return
     */
    public static Date localDateTimeToDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * LocalDateTime转Date
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)){
            return null;
        }
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * LocalDateTime Date 相互转换
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime;
    }
    /**
     * Date转时间字符串
     * @param date
     * @return
     */
    public static String dateTimeToStr(Date date) {
        if (date == null) {
            return "";
        }
        return dateToLocalDateTime(date).format(DEFAULT_DATETIME_FORMATTER);
    }

}
