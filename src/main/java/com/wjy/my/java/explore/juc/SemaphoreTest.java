package com.wjy.my.java.explore.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * juc�����ࣺ�ź���</br>
 * ģ��ͣ����ͣ��
 */
public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {

        // ��Դ����
        int permits = 3;
        Semaphore sp = new Semaphore(permits);

        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                String name = Thread.currentThread().getName();
                try {
                    System.out.printf("��%s����ӭ����ͣ��������ǰʣ�೵λ��%d��\n", name, sp.availablePermits());
                    // �ж���Դʹ����
                    while (sp.availablePermits() <= 0) {
                        System.out.printf("��λ���㣬�롾%s�����ĵȴ�\n", name);
                        Thread.sleep(new Random().nextInt(2 * 1000));
                    }

                    // ������Դ
                    sp.acquire(1);

                    System.out.printf("��%s���ɹ�����ͣ����\n", name);
                    Thread.sleep(new Random().nextInt(8 * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // �ͷ���Դ
                sp.release(1);

                System.out.printf("��%s���ɹ�ʻ��ͣ����\n", name);
            }, i + "�ų�");

            Thread.sleep(1 * 1000);
            t.start();
        }

    }

}
