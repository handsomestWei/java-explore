package com.wjy.my.java.explore.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @see java.lang.invoke.InnerClassLambdaMetafactory#buildCallSite()
 * @see java.lang.invoke.ProxyClassesDumper
 */
public class InnerClassLambdaMetafactoryTest {

    /**
     * cd xxx\my-java-explore\target\classes</br>
     * java -Djdk.internal.lambda.dumpProxyClasses com/wjy/my/java/explore/lambda/InnerClassLambdaMetafactoryTest</br>
     * ���Կ���lambda���ʽ����ʱ���ɵ���ʱ��InnerClassLambdaMetafactoryTest$$Lambda$1.class
     */
    public static void main(String[] args) {
        List<String> ls = Arrays.asList("qwer", "as");
        Collections.sort(ls, (a, b) -> b.compareTo(a));
        // innerClassTest();
    }

    // ���������ڲ�����ʽInnerClassLambdaMetafactoryTest$1.class
    // public void innerClassTest() {
    // Consumer<String> c = new Consumer<String>() {
    // @Override
    // public void accept(String s) {
    // System.out.println(s);
    // }
    // };
    // c.accept("hello lambda");
    // }
}
