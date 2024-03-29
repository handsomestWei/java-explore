package com.wjy.my.java.explore.jvm;

public class HookTest {

    public static void main(String[] args) {
        registerHook();
        System.out.println("close HookTest main");
    }

    private static void registerHook() {
        // jvm中增加一个关闭的钩子，当jvm关闭的时候，会执行系统中已经设置的所有通过方法addShutdownHook添加的钩子，当系统执行完这些钩子后，jvm才会关闭。所以这些钩子可以在jvm关闭的时候进行内存清理、对象销毁等操作。
        Runtime.getRuntime().addShutdownHook(new Thread(HookTest::close));
    }

    private static void close() {
        System.out.println("close");
    }
}
