package org.example;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {

        StudentServiceIfc studentServiceIfc = pretendThatIAmSpringAndInjectStudentService();

        System.out.println(studentServiceIfc.getAllStudents());
        System.out.println(studentServiceIfc.getAllStudents());
    }

    private static StudentServiceIfc pretendThatIAmSpringAndInjectStudentService() {

        StudentService toProxy = new StudentService();

        return (StudentServiceIfc) Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[]{StudentServiceIfc.class},
                new CacheableInvocationHandler(toProxy)
        );
    }
}
