package com.wjy.my.java.explore.reflect;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

import com.wjy.my.java.explore.pojo.Order;

/**
 * 需要在不调用构造函数的情况下实例化对象是一项相当特殊的任务，但是在某些情况下这是有用的：</br>
 * 序列化，远程调用和持久化：对象需要被实例化并恢复到特定的状态，而不需要调用代码</br>
 * 代理、AOP库和mock对象：类可以被子类继承而子类不用担心父类的构造器</br>
 * 容器框架：对象可以以非标准的方式动态地实例化</br>
 * Objenesis旨在通过绕过对象实例化的构造函数来克服这些限制。</br>
 */
public class ObjenesisTest {

    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {

        Objenesis objenesis = new ObjenesisStd();
        ObjectInstantiator odInstantiator = objenesis.getInstantiatorOf(Order.class);
        Order od = (Order) odInstantiator.newInstance();

        System.out.println(od.getClass());
        System.out.println(od.getId());
    }

}
