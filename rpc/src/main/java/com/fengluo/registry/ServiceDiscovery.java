package com.fengluo.registry;

import com.fengluo.common.extension.SPI;
import com.fengluo.remoting.dto.RpcRequest;

import java.net.InetSocketAddress;

/**
 * 服务发现
 *
 * @Author: fengluo
 * @Date: 2022/8/2 14:51
 */
@SPI
public interface ServiceDiscovery {

    InetSocketAddress discoverService(RpcRequest rpcRequest);

}
