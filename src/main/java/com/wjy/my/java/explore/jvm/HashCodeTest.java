package com.wjy.my.java.explore.jvm;

/**
 * hotspot\src\share\vm\runtime\synchronizer.cpp get_next_hash
 *
 * hotspot共支持6中生成hashCode策略，默认策略值是5
 * 策略0: 直接通过随机数生成
 * 策略1: 通过object地址和随机数运算生成
 * 策略2: 永远返回1, 用于测试
 * 策略3: 返回一个全局递增的序列数
 * 策略4: 直接采用object的地址值
 * 策略5: 通过在每个线程中的四个变量: _hashStateX, _hashStateY, _hashStateZ, _hashStateW组合运算出hashCode值，根据计算结果同步修改这个四个值
 *
 * 可通过jvm参数-XX:hashCode=2指定hashcode生成策略
 * IntelliJ IDEA中需要Edit Configurations->Modify Options->Add VM Options才能显示jvm参数项
 * */
public class HashCodeTest {

    public static void main(String[] args) {

        Object obj1 = new Object();
        Object obj2 = new Object();

        System.out.println("obj1 hashcode = " + obj1.hashCode());
        System.out.println("obj2 hashcode = " + obj2.hashCode());
    }
}
