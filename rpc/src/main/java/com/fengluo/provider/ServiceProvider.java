package com.fengluo.provider;

import com.fengluo.common.extension.SPI;
import com.fengluo.config.RpcServiceConfig;

/**
 * @Author: fengluo
 * @Date: 2022/8/3 20:25
 */
@SPI
public interface ServiceProvider {

    /**
     * 增加服务
     *
     * @param rpcServiceConfig
     */
    void addService(RpcServiceConfig rpcServiceConfig);

    /**
     * 获取服务
     *
     * @param rpcServiceName
     * @return
     */
    Object getService(String rpcServiceName);

    /**
     * 发布服务
     *
     * @param rpcServiceConfig
     */
    void publishService(RpcServiceConfig rpcServiceConfig);

}
