package com.robust.jvm;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author bintao
 * @Despription
 * @date 2018/9/29 9:03
 */
public class Jvm32gHeapTest {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000000; i++) {
            Student stu = new Student(
                    (byte)(i%128), (short)(i%256), i, i, i, i, i%2==0?true:false, 'a',
                    (byte)(i%128), (short)(i%256), i, (long)i, (float)i, (double)i,
                    i%2==0?Boolean.TRUE:Boolean.FALSE, 'a', String.valueOf(i));
            if(i>0 && i%100000==0){
                System.out.println("i="+i);
            }
        }
        // 留点时间采集GC信息
        Thread.sleep(20000);
    }
}
@Data
@AllArgsConstructor
class Student{
    private byte a;
    private short b;
    private int c;
    private long d;
    private float e;
    private double f;
    private boolean g;
    private char h;

    private Byte i;
    private Short j;
    private Integer k;
    private Long l;
    private Float m;
    private Double n;
    private Boolean o;
    private Character p;

    private String q;
}
