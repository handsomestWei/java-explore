package com.wjy.my.java.explore.juc;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * juc工具类：可循环利用的屏障。到达屏障点则放开屏障
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {

        int parties = 3;
        CyclicBarrier cb = new CyclicBarrier(parties, () -> {
            // 放开屏障时触发
            System.out.println("放开屏障");
        });

        ExecutorService pool = Executors.newFixedThreadPool(parties);
        for (int i = 0; i < parties; i++) {
            pool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName() + "在屏障点前等待");
                    // 阻塞。到达屏障点则放开屏障唤起线程
                    cb.await();
                    System.out.println(Thread.currentThread().getName() + "通过屏障");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        pool.shutdown();

        // 重置，可复用
        cb.reset();

    }

}
