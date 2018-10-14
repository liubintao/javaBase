package com.robust.blocked;

import java.util.concurrent.TimeUnit;

/**
 * @author bintao
 * @Despription
 * @date 2018/8/6 10:57
 */
public class BlockedTest {
    public static void main(String[] args) {
        final Thread thread = new Thread(){
            @Override
            public void run() {
                synchronized (this){
                    System.out.println(Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start(); //线程进入runnable状态
        thread.setName("thread-1");
        //主线程进入
        synchronized(thread){
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
