package com.fengluo.registry.nacos;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.fengluo.common.extension.ExtensionLoader;
import com.fengluo.loadbalance.LoadBalance;
import com.fengluo.registry.ServiceDiscovery;
import com.fengluo.remoting.dto.RpcRequest;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * 服务注册的 nacos 实现
 *
 * @Author: fengluo
 * @Date: 2022/8/2 14:58
 */
@Slf4j
public class NacosServiceDiscoveryImpl implements ServiceDiscovery {

    private static final String SERVER_ADDR = "127.0.0.1:8848";

    private final LoadBalance loadBalance;

    public NacosServiceDiscoveryImpl(LoadBalance loadBalance) {
        this.loadBalance = ExtensionLoader.getExtensionLoader(LoadBalance.class).getExtension("loadBalance");
    }


    @Override
    public InetSocketAddress discoverService(RpcRequest rpcRequest) {
        try {
            NamingService naming = NamingFactory.createNamingService(SERVER_ADDR);
            List<Instance> allInstances = naming.getAllInstances(rpcRequest.getRpcServiceName());
            Instance instance = loadBalance.selectServiceAddress(allInstances, rpcRequest);
            if (instance != null) {
                String ip = instance.getIp();
                int port = instance.getPort();
                return new InetSocketAddress(ip, port);
            } else {
                throw new RuntimeException("查询不到服务: " + rpcRequest.getRpcServiceName());
            }
        } catch (NacosException e) {
            throw new RuntimeException("查询服务失败！" + e.getMessage());
        }
    }

}
