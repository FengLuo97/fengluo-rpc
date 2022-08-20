package com.fengluo.remoting.dto;

import lombok.*;

import java.io.Serializable;

/**
 * rpc 请求对象
 *
 * @Author: fengluo
 * @Date: 2022/8/2 14:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = 1905122041950251207L;

    /**
     * 请求 id
     */
    private String requestId;

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 请求参数
     */
    private Object[] parameters;

    /**
     * 参数类型
     */
    private Class<?>[] paramTypes;

    /**
     * 版本
     */
    private String version;

    /**
     * 组
     */
    private String group;

    public String getRpcServiceName() {
        return this.getInterfaceName() + this.getGroup() + this.getVersion();
    }

}
