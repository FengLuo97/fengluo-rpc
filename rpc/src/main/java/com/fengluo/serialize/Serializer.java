package com.fengluo.serialize;

import com.fengluo.common.extension.SPI;

/**
 * @Author: fengluo
 * @Date: 2022/8/2 15:06
 */
@SPI
public interface Serializer {

    /**
     * 序列化
     *
     * @param obj
     * @return
     */
    byte[] serialize(Object obj);

    /**
     * 反序列化
     *
     * @param bytes
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz);

}
