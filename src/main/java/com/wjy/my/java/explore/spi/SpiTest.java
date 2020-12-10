package com.wjy.my.java.explore.spi;

import java.util.ServiceLoader;

/**
 * @see java.util.ServiceLoader</br>
 *      ��ȡjar����META-INF\servicesĿ¼��ָ�������ļ������ض�Ӧʵ��
 */
public class SpiTest {

    public static void main(String[] args) {
        ServiceLoader<IHelloSpi> serviceLoader = ServiceLoader.load(IHelloSpi.class);
        for (IHelloSpi helloSPI : serviceLoader) {
            helloSPI.sayHello();
        }
    }

}
