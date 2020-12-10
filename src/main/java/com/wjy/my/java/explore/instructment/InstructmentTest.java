package com.wjy.my.java.explore.instructment;

import com.wjy.my.java.explore.pojo.User;

/**
 * JVM Tool Interface
 */
public class InstructmentTest {

    // jar包的MANIFEST.MF已经指定Premain-Class: com.wjy.my.java.explore.instructment.PreInstructmentTest
    // 运行java -javaagent:my-java-explore-0.0.1.jar -cp my-java-explore-0.0.1.jar com.wjy.my.java.explore.instructment.InstructmentTest
    public static void main(String[] args) {
        System.out.println("InstructmentTest main start");
        new User().sayHello("");
        System.out.println("InstructmentTest main end");
    }

}
