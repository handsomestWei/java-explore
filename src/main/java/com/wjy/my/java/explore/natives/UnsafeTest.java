package com.wjy.my.java.explore.natives;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * ����Unsafe��
 */
public class UnsafeTest {

    public static void main(String[] args) {

        Unsafe unsafe = null;
        try {
            // �ο�sun.misc.UnsafeԴ��
            // @CallerSensitive
            // public static Unsafe getUnsafe() {
            // Class<?> caller = Reflection.getCallerClass();
            // if (!VM.isSystemDomainLoader(caller.getClassLoader()))
            // throw new SecurityException("Unsafe");
            // return theUnsafe;
            // }

            unsafe = Unsafe.getUnsafe();
        } catch (Exception e) {
            // ����ֱ�ӵ��á���ָ��������������ã��׳��쳣
            e.printStackTrace();
        }

        try {
            // �ο�sun.misc.UnsafeԴ��
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

            // ���Է����ȡ�ڲ���˽�о�̬��
            Field field = Unsafe.class.getDeclaredFields()[0];
            field.setAccessible(true);
            // ��nullȡ��staticֵ
            unsafe = (Unsafe) field.get(null);

            System.out.println(unsafe.getClass());
            unsafe.allocateMemory(1024);
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
