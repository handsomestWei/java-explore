package com.wjy.my.java.explore.instructment;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class PreInstructmentTest {

    public static void premain(String agentArgs, Instrumentation inst)
            throws ClassNotFoundException, UnmodifiableClassException {
        inst.addTransformer(new JavassistTransformer());
        System.out.println("PreInstructmentTest premain ok!");
    }

}
