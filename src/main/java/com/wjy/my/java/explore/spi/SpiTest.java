package com.wjy.my.java.explore.spi;

import java.util.ServiceLoader;

/**
 * @see java.util.ServiceLoader</br>
 *      读取jar包里META-INF\services目录下指定包名文件，加载对应实例
 */
public class SpiTest {

    public static void main(String[] args) {
        // 此时还没加载实例
        ServiceLoader<IHelloSpi> serviceLoader = ServiceLoader.load(IHelloSpi.class);
        // ServiceLoader实现了Iterable接口。当使用for语法糖时，返回自定义迭代器，此时才延迟加载实例
        for (IHelloSpi helloSPI : serviceLoader) {
            helloSPI.sayHello();
        }
    }

}
