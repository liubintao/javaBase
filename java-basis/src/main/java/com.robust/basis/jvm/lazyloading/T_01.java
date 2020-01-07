package com.robust.basis.jvm.lazyloading;

/**
 * @Description: 测试类初始化的几种场景
 * @Author: robust
 * @CreateDate: 2019/12/2 15:00
 * @Version: 1.0
 */
public class T_01 {

    public static void main(String[] args) {
//        T t = new T();
//        T.m();
//        T.f = "abc";
        System.out.println(T.name);
    }

    static class T {

        public static String f;
        public static final String F = "a";
        public static String name = "b";

        static {
            System.out.println("inner class T init...");
        }

        static void m() {
            System.out.println("call method m...");
        }
    }
}

