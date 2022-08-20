package com.fengluo;

import lombok.*;

import java.io.Serializable;

/**
 * @Author: fengluo
 * @Date: 2022/8/5 15:41
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Hello implements Serializable {
    private String message;
    private String description;
}

