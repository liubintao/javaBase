package org.itliu.singleton;

import java.util.stream.IntStream;

/**
 * @auther itliu
 * @despription 懒汉式_完美的双重检查锁
 * 使用volatile解决指令重排
 * @data 2020/2/8
 */
public class Singleton06 {
    private static volatile Singleton06 _instance;

    private Singleton06() {
    }

    public static final Singleton06 getInstance() {
        if (_instance == null) {
            synchronized (Singleton06.class) {
                if (_instance == null) {
                    _instance = new Singleton06();
                }
            }
        }
        return _instance;
    }

    public static void main(String[] args) {
        IntStream.range(0, 100).forEach(i -> {
            new Thread(() -> {
                System.out.println(Singleton06.getInstance().hashCode());
            }).start();
        });
    }
}
