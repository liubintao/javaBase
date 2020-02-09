package org.itliu.singleton;

import java.lang.reflect.Constructor;
import java.util.stream.IntStream;

/**
 * @auther itliu
 * @despription 静态内部类方式_防止反射
 * 这种形式兼顾懒汉式的内存浪费，也兼顾Synchronized的性能问题，完美的屏蔽了这两个缺点
 * @data 2020/2/8
 */
public class Singleton08 {
    private static boolean initialized = false;

    /**
     * 默认使用Singleton08的时候，会先初始化内部类，如果没有使用的话，内部类则不加载
     * 内部类只有在外部类调用的时候才会被加载，内部类一定是要在方法调用之前初始化
     */
    private Singleton08() {
        //防止反射入侵
        synchronized (this) {
            if (!initialized) {
                initialized = true;
            } else {
                throw new RuntimeException("单例已被侵犯");
            }
        }
    }

    /**
     * 每一个关键字都不是多余的
     * static 是为了使单例的空间能够共享
     * final 保证方法不会被重写,重载
     *
     * @return
     */
    public static final Singleton08 getInstance() {
        /**
         * 再返回结果之前，内部类一定会先加载
         */
        return Singleton08.Holder.LAZY;
    }

    /**
     * 默认不加载
     */
    private static class Holder {
        private static final Singleton08 LAZY = new Singleton08();
    }

    public static void main(String[] args) throws Exception {
        IntStream.range(0, 100).forEach(i -> {
            new Thread(() -> {
                System.out.println(Singleton08.getInstance().hashCode());
            }).start();
        });

        Constructor<Singleton08> constructor = Singleton08.class.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        Singleton08 instance1 = constructor.newInstance();
        Singleton08 instance2 = constructor.newInstance();
        System.out.println(instance1 == instance2);
    }
}
