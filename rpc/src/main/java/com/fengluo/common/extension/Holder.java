package com.fengluo.common.extension;

/**
 * @Author: fengluo
 * @Date: 2022/8/3 14:27
 */
public class Holder<T> {

    private volatile T value;

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}

