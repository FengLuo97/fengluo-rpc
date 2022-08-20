package com.fengluo.common.constant;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Author: fengluo
 * @Date: 2022/8/2 19:46
 */
public class RpcConstants {

    // 魔数
    public static final byte[] MAGIC_NUMBER = {(byte) 'f', (byte) 'r', (byte) 'p', (byte) 'c'};
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    // 版本信息
    public static final byte VERSION = 1;
    // 总字节长度
    public static final byte TOTAL_LENGTH = 16;
    // 请求类型
    public static final byte REQUEST_TYPE = 1;
    // 响应类型
    public static final byte RESPONSE_TYPE = 2;
    // 心跳请求
    public static final byte HEARTBEAT_REQUEST_TYPE = 3;
    // 心跳响应
    public static final byte HEARTBEAT_RESPONSE_TYPE = 4;
    // 请求头长度
    public static final int HEAD_LENGTH = 16;
    // ping
    public static final String PING = "ping";
    // pong
    public static final String PANG = "pang";
    // 最大帧长度
    public static final int MAX_FRAME_LENGTH = 8 * 1024 * 1024;

}
