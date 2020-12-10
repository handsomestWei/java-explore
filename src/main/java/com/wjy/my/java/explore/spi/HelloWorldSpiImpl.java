package com.wjy.my.java.explore.spi;

public class HelloWorldSpiImpl implements IHelloSpi {

    @Override
    public void sayHello() {
        System.out.println("hello world");
    }

}
