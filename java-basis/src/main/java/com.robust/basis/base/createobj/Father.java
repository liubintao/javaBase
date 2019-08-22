package com.robust.basis.base.createobj;

/**
 * Created by bintao on 2018/4/8.
 */
public class Father {
    public static final String field;

    static {
        field = "a";
        System.out.println("father class init");
    }

    public Father() {

        System.out.println("father construct init");
    }
}
