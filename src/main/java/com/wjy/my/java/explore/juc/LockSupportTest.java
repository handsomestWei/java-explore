package com.wjy.my.java.explore.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * juc�����ࣺ�߳������ͻ���</br>
 * ʵ��ʹ����unsafe���park��unpark����
 */
public class LockSupportTest {
    
    private static String name = "�����߳�";

    public static void main(String[] args) {      

        Thread t = new Thread(() -> {
            try {
                String n = Thread.currentThread().getName();
                System.out.printf("��%s������\n", n);
                TimeUnit.SECONDS.sleep(2);

                // ����
                LockSupport.park();

                System.out.printf("��%s��������\n", n);
                TimeUnit.SECONDS.sleep(2);
                System.out.printf("��%s���������\n", n);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, name);

        t.start();

        try {
            monitorState(t, new int[] { 0, 3 });
            TimeUnit.SECONDS.sleep(2);

            // ����
            LockSupport.unpark(t);

            monitorState(t, new int[] { 2, 2 });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void monitorState(Thread t, int[] frequencySeconds) {
        for (int i : frequencySeconds) {
            monitorState(t, i);
        }
    }

    private static void monitorState(Thread t, int afterSeconds) {
        if (afterSeconds > 0) {
            try {
                TimeUnit.SECONDS.sleep(afterSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("��monitor����%s������״̬��%s��\n", name, t.getState().name());
    }

}
