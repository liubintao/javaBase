package com.robust.singleton.test;

import com.robust.singleton.lazy.LazyOne;
import com.robust.singleton.lazy.LazyTwo;

import java.time.Instant;

/**
 * @author bintao
 * @Despription
 * @date 2018/9/1 11:37
 */
public class LazyPerformanceTest {
    public static void main(String[] args) {
        int count = 200000000;
        long start = Instant.now().toEpochMilli();
        for (int i = 0; i < count; i++) {
            Object obj = LazyOne.getInstance();
        }
        long end = Instant.now().toEpochMilli();
        System.out.println("总耗时：" + (end-start));

        start = Instant.now().toEpochMilli();
        for (int i = 0; i < count; i++) {
            Object obj = LazyTwo.getInstance();
        }
        end = Instant.now().toEpochMilli();
        System.out.println("总耗时：" + (end-start));
    }
}
