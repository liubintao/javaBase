package com.robust.base.createobj;

/**
 * Created by bintao on 2018/4/8.
 */
public class Child extends Father{
    static {
        System.out.println("child class init");
    }

    public Child() {
        System.out.println("child construct init");
    }
}
