package org.itliu.singleton;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @auther itliu
 * @despription 懒汉式_加锁Synchronized
 * 虽然达到了按需初始化的目的，但却带来线程不安全的问题
 * 可以通过synchronized解决，但也带来效率下降
 * @data 2020/2/8
 */
public class Singleton03 {

    private static Singleton03 _instance;

    private Singleton03() {
    }

    public static final synchronized Singleton03 getInstance() {
        if (_instance == null) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            _instance = new Singleton03();
        }
        return _instance;
    }

    public static void main(String[] args) {
        IntStream.range(0, 100).forEach(i -> {
            new Thread(() -> {
                System.out.println(Singleton03.getInstance().hashCode());
            }).start();
        });
    }

}
