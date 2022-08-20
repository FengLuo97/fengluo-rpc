package com.fengluo.service.impl;

import com.fengluo.Hello;
import com.fengluo.HelloService;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: fengluo
 * @Date: 2022/8/5 15:58
 */
@Slf4j
public class HelloServiceImpl2 implements HelloService {

    @Override
    public String hello(Hello hello) {
        System.out.println("HelloServiceImpl2收到: {}." + hello.getMessage());
        String result = "Hello description is " + hello.getDescription();
        System.out.println("HelloServiceImpl2返回: {}." + result);
        return result;
    }
}
