package com.robust.test;

import java.util.Arrays;

/**
 * Created by bintao on 2017/8/9.
 */
public class StringTest {

    public static void main(String[] args) {
        String s = "abcd\r\nefgh";
//        System.out.println(s);
//        System.out.println(Arrays.toString(s.split("\\|")));
        System.out.println(s.replace("\r\n", ""));
    }
}
