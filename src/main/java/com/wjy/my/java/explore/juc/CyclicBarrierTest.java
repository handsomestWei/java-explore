package com.wjy.my.java.explore.juc;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * juc�����ࣺ��ѭ�����õ����ϡ��������ϵ���ſ�����
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {

        int parties = 3;
        CyclicBarrier cb = new CyclicBarrier(parties, () -> {
            // �ſ�����ʱ����
            System.out.println("�ſ�����");
        });

        ExecutorService pool = Executors.newFixedThreadPool(parties);
        for (int i = 0; i < parties; i++) {
            pool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName() + "�����ϵ�ǰ�ȴ�");
                    // �������������ϵ���ſ����ϻ����߳�
                    cb.await();
                    System.out.println(Thread.currentThread().getName() + "ͨ������");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        pool.shutdown();

        // ���ã��ɸ���
        cb.reset();

    }

}
