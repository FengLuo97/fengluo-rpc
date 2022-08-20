package com.fengluo.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: fengluo
 * @Date: 2022/8/3 15:38
 */
@Getter
@AllArgsConstructor
public enum CompressTypeEnum {

    GZIP((byte) 0x01, "gzip");

    private final byte code;
    private final String name;

    public static String getName(byte code) {
        for (CompressTypeEnum c : CompressTypeEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }

}
