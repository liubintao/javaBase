package com.robust.base;

/**
 * Created by bintao on 2017/6/26.
 */
public class StringTest {

    public static void main(String[] args) {
        String a = "a";
        String b = "a";
        System.out.println(a == b);

        System.out.println(a.getBytes().length);
        System.out.println("刘".getBytes().length);

        System.out.println(String.format("%02d", 1));

        System.out.println(String.format("%020d", 1));

        System.out.println(String.format("%06d", 1234567));

        System.out.println("20170922".compareTo("20170923"));
    }
}
