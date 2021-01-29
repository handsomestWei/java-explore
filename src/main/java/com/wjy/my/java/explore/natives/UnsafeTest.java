package com.wjy.my.java.explore.natives;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * 调用Unsafe类
 */
public class UnsafeTest {

    public static void main(String[] args) {

        Unsafe unsafe = null;
        try {
            // 参考sun.misc.Unsafe源码
            // @CallerSensitive
            // public static Unsafe getUnsafe() {
            // Class<?> caller = Reflection.getCallerClass();
            // if (!VM.isSystemDomainLoader(caller.getClassLoader()))
            // throw new SecurityException("Unsafe");
            // return theUnsafe;
            // }

            unsafe = Unsafe.getUnsafe();
        } catch (Exception e) {
            // 不能直接调用。非指定的类加载器调用，抛出异常
            e.printStackTrace();
        }

        try {
            // 参考sun.misc.Unsafe源码
            // public final class Unsafe {
            //
            // private static native void registerNatives();
            // static {
            // registerNatives();
            // sun.reflect.Reflection.registerMethodsToFilter(Unsafe.class, "getUnsafe");
            // }
            //
            // private Unsafe() {}
            //
            // private static final Unsafe theUnsafe = new Unsafe();

            // 可以反射获取内部的私有静态类
            Field field = Unsafe.class.getDeclaredFields()[0];
            field.setAccessible(true);
            // 传null取出static值
            unsafe = (Unsafe) field.get(null);

            System.out.println(unsafe.getClass());
            unsafe.allocateMemory(1024);
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
