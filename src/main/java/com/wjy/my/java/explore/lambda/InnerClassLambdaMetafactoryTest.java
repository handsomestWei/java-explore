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
     * 可以看到lambda表达式运行时生成的临时类InnerClassLambdaMetafactoryTest$$Lambda$1.class
     */
    public static void main(String[] args) {
        List<String> ls = Arrays.asList("qwer", "as");
        Collections.sort(ls, (a, b) -> b.compareTo(a));
        // innerClassTest();
    }

    // 编译生成内部类形式InnerClassLambdaMetafactoryTest$1.class
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
