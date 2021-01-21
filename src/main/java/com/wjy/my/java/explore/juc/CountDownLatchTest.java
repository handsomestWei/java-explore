package com.wjy.my.java.explore.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * juc�����ࣺ����ʱ���ơ����캯��������Դ��countDown��Դ��һ��await�����ȴ���Դȫ���������
 */
public class CountDownLatchTest {

    public static void main(String[] args) {

        // ��ʼ�ź���
        int iStart = 1;
        CountDownLatch cInit = new CountDownLatch(iStart);
        // ׼������ź���
        int iReady = 4;
        CountDownLatch cReady = new CountDownLatch(iReady);

        ExecutorService pool = Executors.newFixedThreadPool(iReady);
        for (int i = 0; i < iReady; i++) {
            pool.execute(() -> {
                try {
                    String name = Thread.currentThread().getName();
                    System.out.printf("��%s���ȴ�\n", name);
                    // �ȴ��ͷ���Դ
                    cInit.await();
                    System.out.printf("��%s����ʼ\n", name);
                    // �ͷ���Դ
                    cReady.countDown();
                    System.out.printf("��%s������\n", name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            System.out.println("��ʼ");
            // �ͷ���Դ
            cInit.countDown();
            // �ȴ��ͷ���Դ
            cReady.await();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("�����߳̽�����");
        } catch (Exception e) {
            e.printStackTrace();
        }

        pool.shutdown();
    }

}
