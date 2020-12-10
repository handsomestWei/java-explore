package com.wjy.my.java.explore.callerSensitive;

import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

/**
 * @see sun.reflect.CallerSensitive</br>
 * @see java.lang.Thread#getContextClassLoader()���õ�</br>
 * @CallerSensitive ������������classloader���أ���rt.jar�����ſ��Ա�ʶ��</br>
 *                  Reflection.getCallerClass()�����������ڵķ���������@CallerSensitive����ע�⣬ͨ���˷�����ȡclassʱ��������·�����е���@CallerSensitiveע��ķ������ֱ࣬��������һ��δʹ�ø�ע�����</br>
 */
public class CallerSensitiveTest {

    // ��Ҫ���jvm����-Xbootclasspath/a:xxx\my-java-explore\target\classes
    @SuppressWarnings("deprecation")
    @CallerSensitive
    public static void main(String[] args) {
        System.out.format("Method is called by %s%n", Reflection.getCallerClass(1));
        System.out.format("Method is called by %s%n", Reflection.getCallerClass());
    }

}
