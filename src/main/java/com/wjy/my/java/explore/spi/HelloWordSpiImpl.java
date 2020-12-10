package com.wjy.my.java.explore.spi;

public class HelloWordSpiImpl implements IHelloSpi {

    @Override
    public void sayHello() {
        System.out.println("hello word");
    }
}
