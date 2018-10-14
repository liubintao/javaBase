package com.robust.singleton.test;

import com.robust.singleton.lazy.LazyThree;

import java.lang.reflect.Constructor;

/**
 * @author bintao
 * @Despription
 * @date 2018/9/1 11:56
 */
public class LazyThreeTest {
    public static void main(String[] args) {
        Class<?> clz = LazyThree.class;
        try {
            /**
             * 通过反射拿到私有的构造方法
             */
            Constructor c = clz.getDeclaredConstructor(null);
            c.setAccessible(true);
            Object obj1 = c.newInstance();
            Object obj2 = c.newInstance();
            System.out.println(obj1 == obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
