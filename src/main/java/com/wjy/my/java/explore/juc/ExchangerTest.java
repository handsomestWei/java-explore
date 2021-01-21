package com.wjy.my.java.explore.juc;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * juc工具类：线程间交换数据
 */
public class ExchangerTest {

    public static void main(String[] args) throws InterruptedException {

        // 创建交换器
        Exchanger<String> ex = new Exchanger<>();

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(() -> {
            try {
                String sendMsg = "线程1发送";
                // 交换消息
                String reciveMsg = ex.exchange(sendMsg);
                System.out.println("线程1接收：" + reciveMsg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                String sendMsg = "线程2发送";
                // 交换消息
                String reciveMsg = ex.exchange(sendMsg);
                System.out.println("线程2接收：" + reciveMsg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        TimeUnit.SECONDS.sleep(3);
        service.shutdown();

    }

}
