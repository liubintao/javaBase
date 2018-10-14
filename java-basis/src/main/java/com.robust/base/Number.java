package com.robust.base;

import java.math.BigDecimal;

/**
 * Created by neil on 2017/4/28.
 */
public class Number {

    public static void main(String[] args) {
        int a = 3;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.valueOf("1100"));


        BigDecimal one = new BigDecimal("9.500000");
        System.out.println(one.divide(new BigDecimal(12)));
    }
}
