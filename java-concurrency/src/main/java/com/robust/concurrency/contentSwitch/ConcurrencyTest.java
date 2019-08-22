package com.robust.concurrency.contentSwitch;

/**
 * @Description: 下面的代码并发执行一定比串行执行快吗？
 *               通过修改count的值发现，当并发执行累加操作不超过百万次时，速度会比串行执行累加操作要慢。那么，
 *               为什么并发执行的速度会比串行慢呢？这是因为线程有创建和上下文切换的开销。
 * @Author: robust
 * @CreateDate: 2019/6/27 16:08
 * @Version: 1.0
 */
public class ConcurrencyTest {
    private static final long count = 10000L;
    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }
    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            int a = 0;
            for (long i = 0; i < count; i++) {
                a += 5;
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        thread.join();
        System.out.println("concurrency :" + time+"ms,b="+b);
    }
    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time+"ms,b="+b+",a="+a);
    }
}
