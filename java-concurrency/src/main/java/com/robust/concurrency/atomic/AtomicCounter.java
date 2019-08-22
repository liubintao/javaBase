package com.robust.concurrency.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: java实现原子操作
 * 补充CAS 实现原子操作的三大问题
 * 1、ABA问题 解决方式 加上版本号1A、2B、3A java 1.5 后提供AtomicStampedReference 检查当前引用是否符合预期引用，并检查当前标志是否等于预期标志
 * 2、循环时间长开销大
 * 3、只能保证一个共享变量的原子操作 取巧方法：多个共享变量合并成一个 AtomicReference 保证引用对象的原子性
 * @Author: robust
 * @CreateDate: 2019/6/27 9:29
 * @Version: 1.0
 */
public class AtomicCounter {
    private AtomicInteger atomicInteger = new AtomicInteger();
    private int count = 0;

    /**
     * 非线程安全计数器
     */
    private void count() {
        count++;
    }

    /**
     * 使用线程安全的原子类实现计数器
     */
    private void safeCount() {
//        atomicInteger.getAndIncrement();
        //自旋
        for (;;) {
            int i = atomicInteger.get();
            boolean flag = atomicInteger.compareAndSet(i, ++i);
            if (flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>(100);
        long start = System.currentTimeMillis();

        AtomicCounter counter = new AtomicCounter();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> {
                for (int k = 0; k < 10000; k++) {
                    counter.count();
                    counter.safeCount();
                }
            });
            threads.add(t);
        }

        threads.stream().forEach((t) -> {
            t.start();

        });

        /**
         * 主线程等待所有子线程执行完
         */
        threads.stream().forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(counter.count);
        System.out.println(counter.atomicInteger.get());
        System.out.println(System.currentTimeMillis() - start);
    }
}
