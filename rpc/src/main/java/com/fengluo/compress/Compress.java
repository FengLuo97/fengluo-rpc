package com.fengluo.compress;

import com.fengluo.common.extension.SPI;

/**
 * @Author: fengluo
 * @Date: 2022/8/3 15:51
 */
@SPI
public interface Compress {

    byte[] compress(byte[] bytes);


    byte[] decompress(byte[] bytes);
}

