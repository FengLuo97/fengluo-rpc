package com.fengluo.registry;

import com.fengluo.common.extension.SPI;

import java.net.InetSocketAddress;

/**
 * 服务注册
 *
 * @Author: fengluo
 * @Date: 2022/8/2 14:51
 */
@SPI
public interface ServiceRegistry {

    /**
     * 服务注册
     *
     * @param rpcServiceName
     * @param inetSocketAddress
     */
    void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress);

}
