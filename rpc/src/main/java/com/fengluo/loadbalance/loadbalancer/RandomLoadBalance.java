package com.fengluo.loadbalance.loadbalancer;

import com.alibaba.nacos.api.naming.pojo.Instance;
import com.fengluo.loadbalance.AbstractLoadBalance;
import com.fengluo.remoting.dto.RpcRequest;

import java.util.List;
import java.util.Random;

/**
 * @Author: fengluo
 * @Date: 2022/8/20 14:59
 */
public class RandomLoadBalance extends AbstractLoadBalance {

    @Override
    protected Instance doSelect(List<Instance> serviceUrlList, RpcRequest rpcRequest) {
        Random random = new Random();
        return serviceUrlList.get(random.nextInt(serviceUrlList.size()));
    }

}
