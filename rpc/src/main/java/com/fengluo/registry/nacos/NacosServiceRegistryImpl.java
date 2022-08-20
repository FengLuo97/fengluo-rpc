package com.fengluo.registry.nacos;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.fengluo.registry.ServiceRegistry;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @Author: fengluo
 * @Date: 2022/8/2 14:59
 */
@Slf4j
public class NacosServiceRegistryImpl implements ServiceRegistry {

    private static final String SERVER_ADDR = "127.0.0.1:8848";

    @Override
    public void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress) {
        try {
            NamingService naming = NamingFactory.createNamingService(SERVER_ADDR);
            naming.registerInstance(rpcServiceName, inetSocketAddress.getAddress().getHostAddress(), inetSocketAddress.getPort());
        } catch (NacosException e) {
            log.info("nacos 注册失败! " + e.getMessage());
        }
    }

}
