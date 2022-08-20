package com.fengluo.remoting.dto;

import lombok.*;

/**
 * rpc 请求对象
 *
 * @Author: fengluo
 * @Date: 2022/8/3 14:10
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class RpcMessage {

    /**
     * rpc 信息类型
     */
    private byte messageType;
    /**
     * 序列化类型
     */
    private byte codec;
    /**
     * 压缩类型
     */
    private byte compress;
    /**
     * 请求 id
     */
    private int requestId;
    /**
     * 请求数据
     */
    private Object data;
}
