package com.wjy.my.java.explore.juc;

/**
 * 美团技术高性能队列Disruptor简介 https://tech.meituan.com/2016/11/18/disruptor.html
 * MESI协议和RFO引发活锁问题 https://www.cnblogs.com/liulaolaiu/p/11744225.html
 *
 * cpu局部性原理：为了提高缓存的命中率，在加载一个数据的同时将它周围的数据也加载进去
 * 伪共享问题：当一个缓存行存储了多个对象时，对其中一个对象的操作，会使得整个缓存行失效。由于CPU和寄存器的工作调配并不是由Java程序员直接负责，所以这种问题很难发现和排查
 * 解决办法：让一个对象充满整个缓存行，空间换时间，避免伪共享
 * */
public class FalseSharingTest implements Runnable {

    public final static long ITERATIONS = 500L * 1000L * 100L;
    private int arrayIndex = 0;
    private static ValuePadding[] longs;

    public FalseSharingTest(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        for (int i = 1; i < 10; i++) {
            System.gc();
            final long start = System.currentTimeMillis();
            runTest(i);
            System.out.println("Thread num " + i + " duration = " + (System.currentTimeMillis() - start));
        }
    }

    // 测试主方法
    private static void runTest(int NUM_THREADS) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        longs = new ValuePadding[NUM_THREADS];
        for (int i = 0; i < longs.length; i++) {
            // 测试时分别替换成ValueNoPadding、ValueJvmPadding
            longs[i] = new ValuePadding();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharingTest(i));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = 0L;
        }
    }

    // 以填充的方式，一个对象占满整个缓存行的64byte
    private final static class ValuePadding {
        // 将一个缓存行使用8个长整型变量全部占满（以单缓存行64byte为单位，其中一个对象的头描述占有8byte，所以实际上只需要7个长整型变量就可以全部占满）
        protected long p1, p2, p3, p4, p5, p6, p7;
        protected volatile long value = 0L;
        protected long p9, p10, p11, p12, p13, p14;
        protected long p15;
    }

    // 不填充，一个缓存行会有多个对象数据
    private final static class ValueNoPadding {
        protected volatile long value = 0L;
    }

    /**
     * 使用@sun.misc.Contended注解，非填充的方式，JVM应当将对象放入不同的缓存行
     * 该注解默认情况下只用于JDK8的原生包，且便于应用者适配jdk7。如果需要在自己的代码中使用该注解，就需要在在启动时程序时携带JVM-XX:-RestrictContended
     * 例如java.lang.Thread#threadLocalRandomSeed等一些代码也有用到
     * 参考http://mail.openjdk.java.net/pipermail/hotspot-dev/2012-November/007309.html
     * */
    @sun.misc.Contended("wjy test")
    private final static class ValueJvmPadding {
        protected volatile long value = 0L;
    }
}
