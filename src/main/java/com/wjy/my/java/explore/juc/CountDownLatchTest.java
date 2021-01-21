package com.wjy.my.java.explore.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * juc工具类：倒计时机制。构造函数创建资源，countDown资源减一，await阻塞等待资源全部消费完毕
 */
public class CountDownLatchTest {

    public static void main(String[] args) {

        // 开始信号量
        int iStart = 1;
        CountDownLatch cInit = new CountDownLatch(iStart);
        // 准备完毕信号量
        int iReady = 4;
        CountDownLatch cReady = new CountDownLatch(iReady);

        ExecutorService pool = Executors.newFixedThreadPool(iReady);
        for (int i = 0; i < iReady; i++) {
            pool.execute(() -> {
                try {
                    String name = Thread.currentThread().getName();
                    System.out.printf("【%s】等待\n", name);
                    // 等待释放资源
                    cInit.await();
                    System.out.printf("【%s】开始\n", name);
                    // 释放资源
                    cReady.countDown();
                    System.out.printf("【%s】结束\n", name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            System.out.println("开始");
            // 释放资源
            cInit.countDown();
            // 等待释放资源
            cReady.await();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("所有线程结束！");
        } catch (Exception e) {
            e.printStackTrace();
        }

        pool.shutdown();
    }

}
