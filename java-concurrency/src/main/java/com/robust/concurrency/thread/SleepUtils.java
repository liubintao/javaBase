package com.robust.concurrency.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by bintao on 2017/7/11.
 */
public class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
}
