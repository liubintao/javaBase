package com.robust.number;

/**
 * Created by liubintao on 2017/6/13.
 */
public class IntegerTest {

    public static void main(String[] args) {
        int a = 128;
        int b = 128;
        System.out.println(a == b);

        Integer c = 127;
        Integer d = 127;
        System.out.println(c == d);

        Integer e = 128;
        Integer f = 128;
        System.out.println(e == f);

        Integer g = Integer.parseInt("128");
        Integer h = Integer.parseInt("128");
        System.out.println(g == h);

        Integer i = Integer.valueOf("128");
        Integer j = Integer.valueOf("128");
        System.out.println(i == j);

        Integer k = Integer.parseInt("127");
        Integer l = Integer.parseInt("127");
        System.out.println(k == l);

        Integer m = Integer.valueOf("127");
        Integer n = Integer.valueOf("127");
        System.out.println(m == n);
    }
}
