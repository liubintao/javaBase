package org.itliu.singleton;

import java.util.stream.IntStream;

/**
 * @auther itliu
 * @despription 懒汉式_双重检查锁
 * 此种写法通过减小锁力度解决了部分效率低下问题，但是还存在并发问题
 * 即：当出现指令重排时，可能出现new出来的对象不可用的情况。new Singleton05() 共分3步：1、分配一块内存 2、初始化 3、将内存地址赋给变量
 * 当指令重排成132的过程，会出问题
 * @data 2020/2/8
 */
public class Singleton05 {
    private static Singleton05 _instance;

    private Singleton05() {
    }

    public static final Singleton05 getInstance() {
        if (_instance == null) {
            synchronized (Singleton05.class) {
                if (_instance == null) {
                    _instance = new Singleton05();
                }
            }
        }
        return _instance;
    }

    public static void main(String[] args) {
        IntStream.range(0, 100).forEach(i -> {
            new Thread(() -> {
                System.out.println(Singleton05.getInstance().hashCode());
            }).start();
        });
    }
}
