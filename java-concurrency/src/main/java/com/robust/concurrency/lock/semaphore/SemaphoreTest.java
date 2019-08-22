package com.robust.concurrency.lock.semaphore;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量测试
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        /**
         * 创建信号量，含有5个许可证，第二个参数true为公平模式
         */
        Semaphore semaphore = new Semaphore(5, true);
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        for(int i = 0; i < 20; i++) {
            final int taskId = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("taskId:" + taskId + " is running...");

                    try {
                        /**
                         * 获取许可
                         */
                        semaphore.acquire();
                        //availablePermits:当前剩余的可用许可。
                        System.out.println( taskId + " Acquired: available:" + semaphore.availablePermits()
                                + " , " + Thread.currentThread().getName()
                                + " , " + new Date());
                        Thread.sleep(1000*10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }

                    /**
                     * 释放许可。
                     */
                    semaphore.release();

                    System.out.println( taskId + " Released: available:" + semaphore.availablePermits()
                            + " , " + Thread.currentThread().getName()
                            + " , " + new Date());
                }
            });
        }
        executorService.shutdown();
    }
}
