package com.robust.basis.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/3/4 16:50
 * @Version: 1.0
 */
public class PhantomReferenceTest {

    public static void main(String[] args) {
        Object counter = new Object();
        ReferenceQueue refQueue = new ReferenceQueue<>();
        PhantomReference<Object> p = new PhantomReference<>(counter, refQueue);
        counter = null;
        System.gc();
        try {
            // Remove 是一个阻塞方法，可以指定 timeout，或者选择一直阻塞
            Reference<Object> ref = refQueue.remove(1000L);
            if (ref != null) {
                // do something
                System.out.println(123);
            }
        } catch (InterruptedException e) {
            // Handle it
            e.printStackTrace();
        }

    }
}
