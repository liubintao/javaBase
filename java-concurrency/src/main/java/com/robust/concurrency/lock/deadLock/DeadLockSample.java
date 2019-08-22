package com.robust.concurrency.lock.deadLock;

import com.robust.concurrency.thread.FindDeadLockThreadSample;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/4/10 15:38
 * @Version: 1.0
 */
public class DeadLockSample extends Thread {
    private String first;
    private String second;

    public DeadLockSample(String name, String first, String second) {
        super(name);
        this.first = first;
        this.second = second;
    }

    public static void main(String[] args) throws InterruptedException {
        FindDeadLockThreadSample.findDeadLock();
        String lockA = "lockA";
        String lockB = "lockB";
        DeadLockSample t1 = new DeadLockSample("Thread1", lockA, lockB);
        DeadLockSample t2 = new DeadLockSample("Thread2", lockB, lockA);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    public void run() {
        synchronized (first) {
            System.out.println(this.getName() + " obtained: " + first);
            try {
                Thread.sleep(1000L);
                System.out.println(this.getName() + " goto obtained: " + second);
                synchronized (second) {
                    System.out.println(this.getName() + " obtained: " + second);
                }
            } catch (InterruptedException e) {
                // Do nothing
            }
        }
    }
}

