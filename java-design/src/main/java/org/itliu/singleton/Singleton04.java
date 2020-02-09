package org.itliu.singleton;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @auther itliu
 * @despription 懒汉式_缩小锁范围
 * @data 2020/2/8
 */
public class Singleton04 {
    private static Singleton04 _instance;

    private Singleton04() {
    }

    public static Singleton04 getInstance() {
        if (_instance == null) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //妄图通过减小同步代码块的方式提高效率，然后不可行
            synchronized (Singleton04.class) {
                _instance = new Singleton04();
            }
        }
        return _instance;
    }

    public static void main(String[] args) {
        IntStream.range(0, 100).forEach(i -> {
            new Thread(() -> {
                System.out.println(Singleton04.getInstance().hashCode());
            }).start();
        });
    }
}
