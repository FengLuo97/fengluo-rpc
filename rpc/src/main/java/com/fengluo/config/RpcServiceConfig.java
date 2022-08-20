package com.fengluo.config;


import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcServiceConfig {

    /**
     * 服务版本
     */
    private String version = "";
    /**
     * 组
     */
    private String group = "";

    /**
     * 服务实例
     */
    private Object service;

    public String getRpcServiceName() {
        return this.getServiceName() + this.getGroup() + this.getVersion();
    }

    public String getServiceName() {
        return this.service.getClass().getInterfaces()[0].getCanonicalName();
    }
}
