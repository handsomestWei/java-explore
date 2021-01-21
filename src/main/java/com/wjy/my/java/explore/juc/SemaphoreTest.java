package com.wjy.my.java.explore.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * juc工具类：信号量</br>
 * 模拟停车场停车
 */
public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {

        // 资源总量
        int permits = 3;
        Semaphore sp = new Semaphore(permits);

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                String name = Thread.currentThread().getName();
                try {
                    System.out.printf("【%s】欢迎进入停车场，当前剩余车位【%d】\n", name, sp.availablePermits());
                    // 判断资源使用量
                    while (sp.availablePermits() <= 0) {
                        System.out.printf("车位不足，请【%s】耐心等待\n", name);
                        Thread.sleep(new Random().nextInt(2 * 1000));
                    }

                    // 申请资源
                    sp.acquire(1);

                    System.out.printf("【%s】成功进入停车场\n", name);
                    Thread.sleep(new Random().nextInt(8 * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 释放资源
                sp.release(1);

                System.out.printf("【%s】成功驶出停车场\n", name);
            }, i + "号车");

            Thread.sleep(1 * 1000);
            t.start();
        }

    }

}
