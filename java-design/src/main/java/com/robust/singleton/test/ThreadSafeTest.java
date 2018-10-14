package com.robust.singleton.test;

import com.robust.singleton.lazy.LazyOne;

import java.time.Instant;
import java.util.concurrent.CountDownLatch;

/**
 * @author bintao
 * @Despription
 * @date 2018/9/1 10:51
 */
public class ThreadSafeTest {
    public static void main(String[] args) {
        int count = 20;
        CountDownLatch latch = new CountDownLatch(count);
        long start = Instant.now().toEpochMilli();
        for (int i = 0; i < count; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        /**
                         * 阻塞，当count=0时就会释放所有的共享锁
                         */
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Object obj = LazyOne.getInstance();
                    System.out.println(Instant.now().toEpochMilli() + ":" + obj.toString());
                }
            }.start();
            latch.countDown();
        }
        long end = Instant.now().toEpochMilli();
        System.out.println("总耗时：" + (end - start));
    }
}
