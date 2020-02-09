package org.itliu.singleton;

import java.lang.reflect.Constructor;
import java.util.stream.IntStream;

/**
 * @auther itliu
 * @despription 静态内部类实现方式
 * JVM保证单例
 * 加载外部类时不会加载内部类，这样可以实现懒加载
 * @data 2020/2/8
 */
public class Singleton07 {

    private Singleton07() {
    }

    public static final Singleton07 getInstance() {
        return Holder._instance;
    }

    private static class Holder {
        private static final Singleton07 _instance = new Singleton07();
    }

    public static void main(String[] args) throws Exception {
        IntStream.range(0, 100).forEach(i -> {
            new Thread(() -> {
                System.out.println(Singleton07.getInstance().hashCode());
            }).start();
        });

        Constructor<Singleton07> constructor = Singleton07.class.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        Singleton07 instance1 = constructor.newInstance();
        Singleton07 instance2 = constructor.newInstance();
        System.out.println(instance1 == instance2);
    }
}
