package com.fengluo.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: fengluo
 * @Date: 2022/8/2 14:22
 */
@AllArgsConstructor
@Getter
public enum RpcResponseCodeEnum {

    SUCCESS(200, "远程调用成功！"),
    FAIL(500, "远程调用失败！");

    private Integer code;
    private String msg;

}
