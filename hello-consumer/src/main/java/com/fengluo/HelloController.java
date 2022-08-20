package com.fengluo;

import com.fengluo.annotation.RpcReference;
import org.springframework.stereotype.Component;

/**
 * @Author: fengluo
 * @Date: 2022/8/5 15:43
 */
@Component
public class HelloController {

    @RpcReference(version = "version1", group = "test1")
    private HelloService helloService;

    public void test() throws InterruptedException {
        this.helloService.hello(new Hello("hello!", "rpc!"));
        for (int i = 0; i < 10; i++) {
            System.out.println(helloService.hello(new Hello("hello!", "rpc!")));
        }
    }

}
