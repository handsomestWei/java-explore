package com.wjy.my.java.explore.juc;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * juc�����ࣺ�̼߳佻������
 */
public class ExchangerTest {

    public static void main(String[] args) throws InterruptedException {

        // ����������
        Exchanger<String> ex = new Exchanger<>();

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(() -> {
            try {
                String sendMsg = "�߳�1����";
                // ������Ϣ
                String reciveMsg = ex.exchange(sendMsg);
                System.out.println("�߳�1���գ�" + reciveMsg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                String sendMsg = "�߳�2����";
                // ������Ϣ
                String reciveMsg = ex.exchange(sendMsg);
                System.out.println("�߳�2���գ�" + reciveMsg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        TimeUnit.SECONDS.sleep(3);
        service.shutdown();

    }

}
