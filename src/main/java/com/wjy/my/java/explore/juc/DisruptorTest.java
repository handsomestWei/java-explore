package com.wjy.my.java.explore.juc;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;

/**
 * 美团技术高性能队列Disruptor简介 https://tech.meituan.com/2016/11/18/disruptor.html
 *
 * disruptor是英国外汇交易公司LMAX开发的一个高性能队列，解决内存队列的延迟问题，在性能测试中发现与I/O操作处于同样的数量级
 * 比jdk自带的的AQS如ArrayBlockingQueue等队列性能更高
 * 1）去掉锁，采用CAS算法
 * 2）使用RingBuffer数组循环有界队列：
 *    2.1）循环队列使用单指针，而不是头尾双指针，简化多线程同步的复杂度。
 *    2.2）数组长度2^n，通过位运算，加快定位的速度，下标采取递增的形式，不用担心index溢出的问题，index是long类型。
 *    2.3）有界队列创建时大小就被固定，避免动态扩容减少系统对内存空间管理的压力。减少垃圾回收带来的停顿，不像链表，Java会定期回收链表中一些不再引用的对象，而数组不会出现空间的新分配和回收问题。
 * 3）提供更多的等待策略
 **/
public class DisruptorTest {

    // 每10ms向disruptor中插入一个元素，消费者读取数据，并打印到终端
    public static void main(String[] args) throws Exception {
        // 队列中的元素
        class Element {

            private int value;

            public int get() {
                return value;
            }

            public void set(int value) {
                this.value = value;
            }

        }

        // 生产者的线程工厂
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "simpleThread");
            }
        };

        // RingBuffer生产工厂,初始化RingBuffer的时候使用
        EventFactory<Element> factory = new EventFactory<Element>() {
            @Override
            public Element newInstance() {
                return new Element();
            }
        };

        // 处理Event的handler
        EventHandler<Element> handler = new EventHandler<Element>() {
            @Override
            public void onEvent(Element element, long sequence, boolean endOfBatch) {
                System.out.println("Element: " + element.get());
            }
        };

        // 阻塞策略
        BlockingWaitStrategy strategy = new BlockingWaitStrategy();

        // 指定RingBuffer的大小
        int bufferSize = 16;

        // 创建disruptor，采用单生产者模式
        Disruptor<Element> disruptor = new Disruptor(factory, bufferSize, threadFactory, ProducerType.SINGLE, strategy);

        // 设置EventHandler
        disruptor.handleEventsWith(handler);

        // 启动disruptor的线程
        disruptor.start();

        RingBuffer<Element> ringBuffer = disruptor.getRingBuffer();

        for (int l = 0; true; l++) {
            // 获取下一个可用位置的下标
            long sequence = ringBuffer.next();
            try {
                // 返回可用位置的元素
                Element event = ringBuffer.get(sequence);
                // 设置该位置元素的值
                event.set(l);
            } finally {
                ringBuffer.publish(sequence);
            }
            Thread.sleep(10);
        }
    }
}
