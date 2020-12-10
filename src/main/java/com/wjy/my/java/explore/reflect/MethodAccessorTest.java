package com.wjy.my.java.explore.reflect;

import java.lang.reflect.Method;

/**
 * @see sun.reflect.DelegatingMethodAccessorImpl</br>
 * @see sun.reflect.NativeMethodAccessorImpl</br>
 * @see https://github.com/openjdk/jdk</br>
 *      ʵ�ʵ�MethodAccessorʵ���������汾��һ����Javaʵ�ֵģ���һ����native codeʵ�ֵġ�</br>
 *      Javaʵ�ֵİ汾�ڳ�ʼ��ʱ��Ҫ�϶�ʱ�䣬��������˵���ܽϺã�native�汾�����෴������ʱ��ԽϿ죬������ʱ�䳤��֮���ٶȾͱȲ���Java���ˡ�����HotSpot���Ż���ʽ�������������ԣ�ͬʱҲ�����������Ĺ�ͬ�㣺��Խnative�߽����Ż����谭���ã������������һ������������Է���Ҳ������������������ʱ�䳤��֮�󷴶����йܰ汾�Ĵ������Щ��</br>
 *      Ϊ��Ȩ�������汾�����ܣ�Sun��JDKʹ���ˡ�inflation���ļ��ɣ���Java�����ڱ��������ʱ����ͷ���ɴ�ʹ��native�棬�ȷ�����ô���������ֵʱ������һ��ר�õ�MethodAccessorʵ���࣬�������е�invoke()�������ֽ��룬�Ժ�Ը�Java�����ķ�����þͻ�ʹ��Java�档
 */
public class MethodAccessorTest {

    // ����ʱ���jvm������-verbose
    @SuppressWarnings("static-access")
    public static void main(String[] args) throws Exception {

        // ��ֵ�ﵽ�󴥷�Loaded sun.reflect.GeneratedMethodAccessor1 from __JVM_DefineClass__
        // ����ÿ����һ���ۼӣ����ɷ���+���� {@link sun.reflect.MethodAccessorGenerator}
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
