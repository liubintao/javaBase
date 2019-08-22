package com.robust.basis.base;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;

/**
 * Created by neil on 2017/4/28.
 */
public class Number {

    public Class aa = MethodHandles.lookup().lookupClass();

    public static void test(int a) {
        a = 4;
        System.out.println(a);
    }

    public static int add(int a) {
        a = 4;
        System.out.println(a);
        return a;
    }

    public static void main(String[] args) {
        int a = 3;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.valueOf("1100"));


        BigDecimal one = new BigDecimal("9.500000");
//        System.out.println(one.divide(new BigDecimal(12)));

//        System.out.println(aa);

        test(a);
        System.out.println(a);

        add(a);
        System.out.println(a);

        a = add(a);
        System.out.println(a);

        System.out.println(Long.MAX_VALUE);
        System.out.println(86400000*100 - Integer.MAX_VALUE);
        System.out.println(86400000*100);
    }
}
