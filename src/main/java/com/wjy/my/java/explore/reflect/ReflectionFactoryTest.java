package com.wjy.my.java.explore.reflect;

import java.lang.reflect.Constructor;

import com.wjy.my.java.explore.pojo.Order;

import sun.reflect.ReflectionFactory;

/**
 * @see sun.reflect.ReflectionFactory</br>
 * @see sun.reflect.MethodAccessorGenerator</br>
 *      无需默认构造函数就可以反射生成对象，这个属性在很多的序列框架可以使用，比如 xml 转换成 bean，有了这个特性对 bean的 class 就没有特殊的要求，无需强制的构造函数就可以生成相应的bean的对象
 */
public class ReflectionFactoryTest {

    public static void main(String[] args) throws Exception {

        ReflectionFactory reflectionFactory = ReflectionFactory.getReflectionFactory();
        // 使用MethodAccessorGenerator的generateSerializationConstructor通过字节码的方式生成构造函数
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
