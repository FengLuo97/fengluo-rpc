package com.fengluo.remoting.transport;

import com.fengluo.common.extension.SPI;
import com.fengluo.remoting.dto.RpcRequest;

/**
 * @Author: fengluo
 * @Date: 2022/8/2 14:11
 */
@SPI
public interface RpcRequestTransport {

    /**
     * 向服务端发送 rpc 请求并获取结果
     * @param rpcRequest
     * @return
     */
    Object sendRpcRequest(RpcRequest rpcRequest);

}
