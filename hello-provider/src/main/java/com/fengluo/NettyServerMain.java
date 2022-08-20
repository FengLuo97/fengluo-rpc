package com.fengluo;

import com.fengluo.annotation.RpcScan;
import com.fengluo.config.RpcServiceConfig;
import com.fengluo.remoting.transport.netty.server.NettyRpcServer;
import com.fengluo.service.impl.HelloServiceImpl2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: fengluo
 * @Date: 2022/8/5 15:59
 */
@RpcScan(basePackage = {"com.fengluo"})
public class NettyServerMain {

    public static void main(String[] args) {
        // Register service via annotation
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(NettyServerMain.class);
        NettyRpcServer nettyRpcServer = (NettyRpcServer) applicationContext.getBean("nettyRpcServer");
        // Register service manually
        HelloService helloService2 = new HelloServiceImpl2();
        RpcServiceConfig rpcServiceConfig = RpcServiceConfig.builder()
                .group("test2").version("version2").service(helloService2).build();
        nettyRpcServer.registerService(rpcServiceConfig);
        nettyRpcServer.start();
    }

}
