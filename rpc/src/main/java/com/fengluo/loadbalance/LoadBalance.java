package com.fengluo.loadbalance;

import com.alibaba.nacos.api.naming.pojo.Instance;
import com.fengluo.common.extension.SPI;
import com.fengluo.remoting.dto.RpcRequest;

import java.util.List;

/**
 * @Author: fengluo
 * @Date: 2022/8/20 14:40
 */
@SPI
public interface LoadBalance {

    /**
     * 负载均衡
     *
     * @param serviceUrlList
     * @param rpcRequest
     * @return
     */
    Instance selectServiceAddress(List<Instance> serviceUrlList, RpcRequest rpcRequest);

}
