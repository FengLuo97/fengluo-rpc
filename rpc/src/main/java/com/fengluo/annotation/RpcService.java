package com.fengluo.annotation;

import java.lang.annotation.*;

/**
 * 服务方注册服务
 *
 * @Author: fengluo
 * @Date: 2022/8/5 14:55
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface RpcService {

    String version() default "";

    String group() default "";

}
