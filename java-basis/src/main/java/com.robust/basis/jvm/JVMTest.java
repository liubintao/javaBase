package com.robust.basis.jvm;

/**
 * Created by bintao on 2017/6/30.
 */
public class JVMTest {
    public static void main(String[] args) {
        // 每次new分配1M内存, 24次分配, 8， 16， 24次分别Young GC
        for(int i=0; i<24; i++) {
            byte[] byte1m = new byte[1 * 1024 * 1024];
        }
        // 10M内存Eden区容不下, S区也容不下, 直接进入Old区, 但是还不够30*50%=15M CMS GC触发条件
        byte[] byte10m = new byte[10 * 1024 * 1024];
        // 再分配10M 就够CMS GC触发条件了
        byte[] byte10m2 = new byte[10 * 1024 * 1024];


        try {
            Thread.sleep(1000);
            //        byte[] byte10m3 = new byte[10 * 1024 * 1024];
            System.out.println("hello world");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
