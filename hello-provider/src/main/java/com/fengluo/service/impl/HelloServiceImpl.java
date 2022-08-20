package com.fengluo.service.impl;

import com.fengluo.Hello;
import com.fengluo.HelloService;
import com.fengluo.annotation.RpcService;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: fengluo
 * @Date: 2022/8/5 15:56
 */
@Slf4j
@RpcService(group = "test1", version = "version1")
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(Hello hello) {
        System.out.println(("HelloServiceImpl 收到：{}" + hello.getMessage()));
        String result = "Hello Description is " + hello.getDescription();
        System.out.println(("HelloServiceImpl 返回：{}" + result));
        return result;
    }
}
