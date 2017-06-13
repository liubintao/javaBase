package org.btliu.shift;

import java.util.Objects;

/**
 * Created by neil on 2017/5/4.
 */
public class Shift {

    public static void main(String[] args) {
        System.out.println(1 << 4);
        System.out.println(1 << 30);

        System.out.println(Objects.hashCode("aaa"));
        System.out.println(Objects.hashCode("bbb"));

        int h;
        Object object = "abc";
        System.out.println((h = object.hashCode()) ^ h >>> 16);
    }
}
