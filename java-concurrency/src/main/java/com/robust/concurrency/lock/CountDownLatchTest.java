package com.robust.concurrency.lock;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static class ProcessingThread extends Thread {
        private final String ident;
        private final CountDownLatch latch;

        public ProcessingThread(String ident, CountDownLatch latch) {
            this.ident = ident;
            this.latch = latch;
        }

        public String getIdentifier() {
            return ident;
        }
        public void initialize() {
            System.out.println(latch.toString());
            latch.countDown();
            System.out.println(Thread.currentThread().getName());
        }
        public void run() {
            initialize();
        }
    }

    public static void main(String[] args) {
        int MAX_THREADS = Runtime.getRuntime().availableProcessors();
        final int quorum = 1 + MAX_THREADS/ 2;
        final CountDownLatch cdl = new CountDownLatch(quorum);
        final Set<ProcessingThread> nodes = new HashSet<>();
        try {
            for (int i=0; i<MAX_THREADS; i++) {
                ProcessingThread local = new ProcessingThread("localhost:"+(9000 + i), cdl);
                nodes.add(local);
                local.start();
            }
//􁬡􀚩quorum􀒅􀭏􀦤􀝎􁭆􀹅􀷛
            cdl.await();
        } catch (InterruptedException e) {
        } finally {
        }
    }
}
