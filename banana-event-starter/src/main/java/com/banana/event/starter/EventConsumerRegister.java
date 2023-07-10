package com.banana.event.starter;

import cn.hutool.core.collection.CollectionUtil;
import com.banana.event.starter.base.Event;
import com.banana.event.starter.base.EventConsumerTask;
import com.banana.event.starter.base.EventException;
import com.banana.event.starter.base.EventType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 通用事件注册器
 * @author: banana
 * @date: 2022/8/17 10:27 下午
 * @version: 1.0
 */
public class EventConsumerRegister {
    /** 事件唯一标识domainCode(领域编号) + aggregateCode（聚合编号） + eventCode（事件编号） */
    private static final String EVENT_SIGN_TEMPLATE = "{0}_{1}_{2}";
    /** 事件类型和处理器集合map */
    private static final Map<String, List<WrapperEventConsumer>> CONSUMER_MAP = Maps.newConcurrentMap();
    /** 事件类型和处理器集合名称map，同一个事件不能存在同名的事件处理器 */
    private static final Map<String, Set<String>> EVENT_MAP_CONSUMER_CODE = Maps.newConcurrentMap();
    /** 每个事件的分数，订阅这个事件的处理器越多，分值越高，分支由订阅的处理器分数累加得到 */
    private static final Map<String, Integer> EVENT_SCORE = Maps.newConcurrentMap();
    /** */
    private static final Map<String, String> EVENT_MAP_CONSUMER_STRING = Maps.newConcurrentMap();


    /**
     * 注册事件处理器
     * 在注册handler时，内部包装EventConsumer避免方法自调用（导致AOP失效）
     * 1、注册的处理器在处理事件时需要保证起幂等性。
     * @param eventType
     * @param wrapper
     */
    public <T>void register(EventType eventType, WrapperEventConsumer wrapper){
        String typeId = getSign(eventType);
        String consumerCode = wrapper.getConsumerCode();
        Set<String> names = EVENT_MAP_CONSUMER_CODE.get(typeId);
        if (!CollectionUtil.isEmpty(names)) {
            if (names.contains(consumerCode)){
                throw new EventException("EventConsumer multi handler consumerCode=" + consumerCode);
            }
        }

        Set<String> nameSet = EVENT_MAP_CONSUMER_CODE.computeIfAbsent(typeId, k-> Sets.newHashSet());
        nameSet.add(consumerCode);

        Integer capacity = EVENT_SCORE.computeIfAbsent(typeId, k-> 0) + 1;
        EVENT_SCORE.put(typeId, capacity);
        // 遍历监听器，查找处理事件的方法
        List<WrapperEventConsumer> handlers = CONSUMER_MAP.computeIfAbsent(typeId, k-> Lists.newArrayList());
        handlers.add(wrapper);
    }


    /**
     * 获取所有的事件处理器
     * @param event
     */
    public static List<WrapperEventConsumer> consumers(Event event){
        String sign = getSign(event);
        return CONSUMER_MAP.get(sign);
    }

    /**
     * 获取同步的事件处理器
     * @param event
     */
    public static WrapperEventConsumer syncConsumer(Event event, String consumerCode){
        String sign = getSign(event);
        List<WrapperEventConsumer> consumers = CONSUMER_MAP.get(sign);
        for (WrapperEventConsumer consumer : consumers) {
            if (StringUtils.equals(consumerCode, consumer.getConsumerCode()) && !consumer.getAsync()){
                return consumer;
            }
        }
        throw new NullPointerException("consumerCode not exist consumerCode:" + consumerCode + " sign:" + sign);
    }

    /**
     * 获取异步事件处理器
     */
    public static List<WrapperEventConsumer> asyncConsumers(String eventTypeId, Set<EventConsumerTask> tasks){
        if (CollectionUtil.isEmpty(tasks)){
            return Collections.emptyList();
        }
        Set<String> taskCodes = tasks.stream().map(EventConsumerTask::getConsumerCode).collect(Collectors.toSet());
        List<WrapperEventConsumer> consumers = CONSUMER_MAP.get(eventTypeId);
        List<WrapperEventConsumer> res = Lists.newArrayListWithExpectedSize(tasks.size());
        for (WrapperEventConsumer consumer : consumers) {
            if (taskCodes.contains(consumer.getConsumerCode()) && consumer.getAsync()){
                res.add(consumer);
            }
        }
        return res;
    }


    /**
     * 获取异步事件处理器
     * @param event 事件类型唯一标识
     * @param consumerCode 处理器编号
     */
    public static WrapperEventConsumer getConsumer(Event event, String consumerCode){
        if (StringUtils.isBlank(consumerCode)){
            return null;
        }

        String sign = getSign(event);
        List<WrapperEventConsumer> consumers = CONSUMER_MAP.get(sign);
        for (WrapperEventConsumer consumer : consumers) {
            if (StringUtils.equals(consumerCode, consumer.getConsumerCode())){
                return consumer;
            }
        }
        throw new NullPointerException("consumerCode not exist consumerCode:" + consumerCode + " sign:" + sign);
    }

    /**
     * 获取事件编号的分数
     * @param eventType
     */
    public static Integer getScore(EventType eventType){
        String sign = getSign(eventType);
        return EVENT_SCORE.get(sign);
    }

    /**
     * 获取事件分数
     * @param eventType
     */
    public static String getConsumerCodes(EventType eventType){
        String sign = getSign(eventType);
        String names = EVENT_MAP_CONSUMER_STRING.get(sign);
        if (names != null){
            return names;
        }

        Set<String> set = EVENT_MAP_CONSUMER_CODE.get(sign);
        if (set == null || set.isEmpty()){
            return "[]";
        }
        StringBuilder res = new StringBuilder("[");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            res.append(iterator.next()).append(",");
        }
        res.replace(res.length()-1, res.length(), "]");

        EVENT_MAP_CONSUMER_STRING.put(sign, res.toString());
        return res.toString();
    }


    /**
     *
     * @param eventType
     * @return
     */
    private static String getSign(EventType eventType){
        return MessageFormat.format(EVENT_SIGN_TEMPLATE, eventType.getAggregateType().getDomain().getCode(),
                eventType.getAggregateType().getCode(), eventType.getCode());
    }

    /**
     *
     * @param event
     * @return
     */
    private static String getSign(Event event){
        return MessageFormat.format(EVENT_SIGN_TEMPLATE, event.getDomainCode(), event.getAggregateCode(), event.getEventCode());
    }


}
