package com.robust.concurrency.blockingQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 阻塞队列
 * @Author: robust
 * @CreateDate: 2019/6/27 11:07
 * @Version: 1.0
 */
public class BlockedQueue<T> {
    private Object[] items;

    private int count;

    private Lock lock = new ReentrantLock();
    private Condition notEmpty;
    private Condition notFull;

    public BlockedQueue(int capacity) {
        if(capacity < 0) {
            throw new IllegalArgumentException();
        }
        this.items = new Object[capacity];
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    private void enq(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count >= items.length) {
                notFull.await();
            }

            /**
             * 省略入队操作
             */
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private void deq() throws InterruptedException {
        lock.lock();

        try {
            while (count <= 0) {
                notEmpty.await();
            }

            /**
             * 省略出队操作
             */
            notFull.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
