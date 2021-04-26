package com.wjy.my.java.explore.reflect;

import java.lang.reflect.Method;

/**
 * @see sun.reflect.DelegatingMethodAccessorImpl</br>
 * @see sun.reflect.NativeMethodAccessorImpl</br>
 * @see https://github.com/openjdk/jdk</br>
 *      实际的MethodAccessor实现有两个版本，一个是Java实现的，另一个是native code实现的。</br>
 *      Java实现的版本在初始化时需要较多时间，但长久来说性能较好；native版本正好相反，启动时相对较快，但运行时间长了之后速度就比不过Java版了。这是HotSpot的优化方式带来的性能特性，同时也是许多虚拟机的共同点：跨越native边界会对优化有阻碍作用，它就像个黑箱一样让虚拟机难以分析也将其内联，于是运行时间长了之后反而是托管版本的代码更快些。</br>
 *      为了权衡两个版本的性能，Sun的JDK使用了“inflation”的技巧：让Java方法在被反射调用时，开头若干次使用native版，等反射调用次数超过阈值时则生成一个专用的MethodAccessor实现类，生成其中的invoke()方法的字节码，以后对该Java方法的反射调用就会使用Java版。
 */
public class MethodAccessorTest {

    // 运行时添加jvm参数：-verbose
    @SuppressWarnings("static-access")
    public static void main(String[] args) throws Exception {

        // 阈值达到后触发Loaded sun.reflect.GeneratedMethodAccessor1 from __JVM_DefineClass__
        // 后续每调用一次累加，生成方法+数字 {@link sun.reflect.MethodAccessorGenerator}
        int threshold = 16;

        Class<?> clz = Class.forName("com.wjy.my.java.explore.pojo.User");
        Object o = clz.newInstance();
        Method m = clz.getMethod("sayHello", String.class);
        for (int i = 0; i < threshold; i++) {
            Thread.currentThread().sleep(2 * 1000);
            m.invoke(o, Integer.toString(i));
        }
    }
}
