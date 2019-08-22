package com.robust.concurrency.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/4/11 11:42
 * @Version: 1.0
 */
public class FindDeadLockThreadSample {

    public static void findDeadLock() {
        ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
        Runnable dlCheck = () -> {
            long[] threadIds = mbean.findDeadlockedThreads();
            if (threadIds != null) {
                ThreadInfo[] threadInfoArray = mbean.getThreadInfo(threadIds);
                System.out.println("Detected deadlock threads:");
                for (ThreadInfo threadInfo : threadInfoArray) {
                    System.out.println(threadInfo.getThreadName());
                    System.out.println(threadInfo.toString());
                }
            }
        };

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        // 稍等 5 秒，然后每 10 秒进行一次死锁扫描
        scheduler.scheduleAtFixedRate(dlCheck, 5L, 10L, TimeUnit.SECONDS);
    }
    public static void main(String[] args) throws InterruptedException {

        findDeadLock();
    }

}
