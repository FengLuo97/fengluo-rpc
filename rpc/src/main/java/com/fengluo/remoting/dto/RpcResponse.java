package com.fengluo.remoting.dto;

import com.fengluo.common.constant.RpcResponseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: fengluo
 * @Date: 2022/8/2 14:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RpcResponse<T> implements Serializable {

    private static final long serialVersionUID = 715745410605631233L;

    /**
     * 响应 id
     */
    private String requestId;

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应体
     */
    private T data;

    public static <T> RpcResponse<T> success(T data, String requestId) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setRequestId(requestId);
        response.setCode(RpcResponseCodeEnum.SUCCESS.getCode());
        response.setMsg(RpcResponseCodeEnum.SUCCESS.getMsg());
        response.setData(data);
        return response;
    }

    public static <T> RpcResponse<T> fail(RpcResponseCodeEnum rpcResponseCodeEnum) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setCode(rpcResponseCodeEnum.getCode());
        response.setMsg(rpcResponseCodeEnum.getMsg());
        return response;
    }

}
