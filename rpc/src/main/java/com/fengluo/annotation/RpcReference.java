package com.fengluo.annotation;

import java.lang.annotation.*;

/**
 * rpc 调用 注解, 声明为 rpc 调用
 *
 * @Author: fengluo
 * @Date: 2022/8/5 14:47
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
public @interface RpcReference {

    String version() default "";

    String group() default "";

}
