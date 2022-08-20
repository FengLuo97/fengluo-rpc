package com.fengluo.remoting.handler;

import com.fengluo.common.exception.RpcException;
import com.fengluo.common.factory.SingletonFactory;
import com.fengluo.provider.ServiceProvider;
import com.fengluo.provider.nacos.NacosServiceProviderImpl;
import com.fengluo.remoting.dto.RpcRequest;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Rpc 请求处理
 *
 * @Author: fengluo
 * @Date: 2022/8/3 20:54
 */
@Slf4j
public class RpcRequestHandler {

    private final ServiceProvider serviceProvider;

    public RpcRequestHandler() {
        serviceProvider = SingletonFactory.getInstance(NacosServiceProviderImpl.class);
    }

    /**
     * 处理 rpc 请求，调用相关方法并返回结果
     *
     * @param rpcRequest
     * @return
     */
    public Object handle(RpcRequest rpcRequest) {
        Object service = serviceProvider.getService(rpcRequest.getRpcServiceName());
        return invokeTargetMethod(rpcRequest, service);
    }

    /**
     * 获取方法执行结果
     *
     * @param rpcRequest
     * @param service
     * @return
     */
    private Object invokeTargetMethod(RpcRequest rpcRequest, Object service) {
        Object result;
        try {
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
            result = method.invoke(service, rpcRequest.getParameters());
            log.info("service:[{}] successful invoke method:[{}]", rpcRequest.getInterfaceName(), rpcRequest.getMethodName());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RpcException(e.getMessage(), e);
        }
        return result;
    }

}
