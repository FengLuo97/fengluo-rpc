package com.fengluo.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: fengluo
 * @Date: 2022/8/3 15:56
 */
@AllArgsConstructor
@Getter
public enum SerializationTypeEnum {

    KRYO((byte) 0x01, "kryo");

    private final byte code;
    private final String name;

    public static String getName(byte code) {
        for (SerializationTypeEnum c : SerializationTypeEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }

}
