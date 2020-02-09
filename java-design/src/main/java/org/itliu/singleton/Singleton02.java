package org.itliu.singleton;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @auther itliu
 * @despription 懒汉式_非线程安全
 * 缺点：虽然达到了按需初始化的目的，但却带来线程不安全的问题
 * 此种写法是有问题的
 * @data 2020/2/8
 */
public class Singleton02 {
    private static Singleton02 _instance;

    private Singleton02() {
    }

    public static final Singleton02 getInstance() {
        if (_instance == null) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            _instance = new Singleton02();
        }
        return _instance;
    }

    public static void main(String[] args) {
        IntStream.range(0, 100).forEach(i -> {
            new Thread(() ->
                    System.out.println(Singleton02.getInstance().hashCode())
            ).start();
        });
    }
}
