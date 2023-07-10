package com.banana.event.starter.base;

/**
 * 唯一标识
 * @author: banana
 * @date 2023-07-05 18:03
 */
public interface Identify<T> {

    /** 获取对象唯一标识，尽量保证ID的值简单且有序 */
    T getUniqueId();

}
