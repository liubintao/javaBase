package com.robust.basis.test;

/**
 * Created by bintao on 2017/8/14.
 */
public class VolatileLong {
    /** 
              * 大小计算: 
              *  long 8 字节 
              * [1]java对象头: 32位 :8 byte 64位:12 byte  
              * 所以总共: 6 个填充 * 8 byte + 8 (value) + 8 (对象头) = 64 byte  
              *  还有采用如下方式的 
              *  long p1, p2, p3, p4, p5, p6, p7; // cache line padding  -> 7 *8 = 56 字节 
              *  long value;  ->  8 字节 
              *  long p8, p9, p10, p11, p12, p13, p14; // cache line padding -> 7*8 = 56 字节 
              *   
              *  java.util.concurrency.Exchanger.Slot<V> 
              *          // Improve likelihood of isolation on <= 64 byte cache lines 
              *  long q0, q1, q2, q3, q4, q5, q6, q7, q8, q9, qa, qb, qc, qd, qe;  15 * 8 
              *  不知道为啥是这样实现:因为父类还有一个Long值 所以总的来说已经超过128了 
              *   
              */
            public volatile long value = 0L;
            public long p1, p2, p3, p4, p5, p6; // comment out  
            //objectsize = 6*8 + 8 + 4 =
}
