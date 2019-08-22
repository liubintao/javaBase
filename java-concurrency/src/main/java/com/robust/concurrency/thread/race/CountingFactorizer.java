package com.robust.concurrency.thread.race;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/4/16 10:23
 * @Version: 1.0
 */
public class CountingFactorizer {

    private volatile boolean flag;

    private static AtomicLong count = new AtomicLong(0);

    public static long getCount() {
        return count.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {CountingFactorizer.getCount();};
        for(int i = 0; i < 10;i++){
            Thread t1 = new Thread(r);
            t1.start();
            t1.join();
            Thread t2 = new Thread(r);
            t2.start();
            t2.join();
        }
        System.out.println(CountingFactorizer.count.get());
    }
}
