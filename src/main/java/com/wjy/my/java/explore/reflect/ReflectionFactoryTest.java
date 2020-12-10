package com.wjy.my.java.explore.reflect;

import java.lang.reflect.Constructor;

import com.wjy.my.java.explore.pojo.Order;

import sun.reflect.ReflectionFactory;

/**
 * @see sun.reflect.ReflectionFactory</br>
 * @see sun.reflect.MethodAccessorGenerator</br>
 *      ����Ĭ�Ϲ��캯���Ϳ��Է������ɶ�����������ںܶ�����п�ܿ���ʹ�ã����� xml ת���� bean������������Զ� bean�� class ��û�������Ҫ������ǿ�ƵĹ��캯���Ϳ���������Ӧ��bean�Ķ���
 */
public class ReflectionFactoryTest {

    public static void main(String[] args) throws Exception {

        ReflectionFactory reflectionFactory = ReflectionFactory.getReflectionFactory();
        // ʹ��MethodAccessorGenerator��generateSerializationConstructorͨ���ֽ���ķ�ʽ���ɹ��캯��
        Constructor<?> constructor = reflectionFactory
                .newConstructorForSerialization(Order.class, Object.class.getDeclaredConstructor());
        constructor.setAccessible(true);
        Object instance = constructor.newInstance();

        System.out.println(instance.getClass().getName());
        if (instance instanceof Order) {
            System.out.println(((Order) instance).getId());
        }
    }
}
