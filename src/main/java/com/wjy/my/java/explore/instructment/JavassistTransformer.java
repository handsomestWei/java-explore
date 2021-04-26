package com.wjy.my.java.explore.instructment;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * 使用Javassist增加字节码
 */
public class JavassistTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("transform " + className);
        if (className.equals("com/wjy/my/java/explore/pojo/User")) {
            ClassPool classPool = ClassPool.getDefault();
            try {
                CtClass clz = classPool.get(className.replaceAll("/", "."));
                CtMethod ctMethod = clz.getDeclaredMethod("sayHello");
                if (!ctMethod.isEmpty()) {
                    ctMethod.insertBefore("System.out.println(\"before sayHello by JavassistTransformer\");");
                }
                System.out.println("JavassistTransformer " + className);
                return clz.toBytecode();
            } catch (NotFoundException | CannotCompileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
