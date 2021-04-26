package com.wjy.my.java.explore.callerSensitive;

import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

/**
 * @see sun.reflect.CallerSensitive</br>
 * @see java.lang.Thread#getContextClassLoader()有用到</br>
 * @CallerSensitive 必须由启动类classloader加载（如rt.jar），才可以被识别</br>
 *                  Reflection.getCallerClass()方法调用所在的方法必须用@CallerSensitive进行注解，通过此方法获取class时会跳过链路上所有的有@CallerSensitive注解的方法的类，直到遇到第一个未使用该注解的类</br>
 */
public class CallerSensitiveTest {

    // 需要添加jvm参数-Xbootclasspath/a:xxx\my-java-explore\target\classes
    @SuppressWarnings("deprecation")
    @CallerSensitive
    public static void main(String[] args) {
        System.out.format("Method is called by %s%n", Reflection.getCallerClass(1));
        System.out.format("Method is called by %s%n", Reflection.getCallerClass());
    }

}
