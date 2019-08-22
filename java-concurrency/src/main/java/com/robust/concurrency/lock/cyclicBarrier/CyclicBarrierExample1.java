package com.robust.concurrency.lock.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/4/9 16:32
 * @Version: 1.0
 */
//@Slf4j
public class CyclicBarrierExample1 {

    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
//                    log.error("exception", e);
                }
            });
        }
        executor.shutdown();
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        System.out.printf("%s is ready", threadNum);
        System.out.println();
//        log.info("{} is ready", threadNum);
        barrier.await();
        System.out.printf("%s continue", threadNum);
        System.out.println();
//        log.info("{} continue", threadNum);
    }
}