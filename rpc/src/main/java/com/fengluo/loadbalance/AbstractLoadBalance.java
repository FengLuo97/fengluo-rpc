package com.fengluo.loadbalance;

import com.alibaba.nacos.api.naming.pojo.Instance;
import com.fengluo.remoting.dto.RpcRequest;

import java.util.Collections;
import java.util.List;

/**
 * @Author: fengluo
 * @Date: 2022/8/20 14:54
 */
public abstract class AbstractLoadBalance implements LoadBalance {

    @Override
    public Instance selectServiceAddress(List<Instance> serviceUrlList, RpcRequest rpcRequest) {
        if (serviceUrlList == null || serviceUrlList.isEmpty()) {
            return null;
        }
        if (serviceUrlList.size() == 1) {
            return serviceUrlList.get(0);
        }
        return doSelect(serviceUrlList, rpcRequest);
    }

    protected abstract Instance doSelect(List<Instance> serviceUrlList, RpcRequest rpcRequest);

}
