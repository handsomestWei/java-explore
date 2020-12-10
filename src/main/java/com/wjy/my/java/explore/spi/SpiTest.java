package com.wjy.my.java.explore.spi;

import java.util.ServiceLoader;

/**
 * @see java.util.ServiceLoader</br>
 *      读取jar包里META-INF\services目录下指定包名文件，加载对应实例
 */
public class SpiTest {

    public static void main(String[] args) {
        ServiceLoader<IHelloSpi> serviceLoader = ServiceLoader.load(IHelloSpi.class);
        for (IHelloSpi helloSPI : serviceLoader) {
            helloSPI.sayHello();
        }
    }

}
