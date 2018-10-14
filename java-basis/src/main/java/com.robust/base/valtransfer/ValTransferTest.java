package com.robust.base.valtransfer;

import java.util.ArrayList;
import java.util.List;

/**
 * 值传递测试
 * Created by bintao on 2018/4/8.
 */
public class ValTransferTest {

    /**
     * 形参list指向main方法中strs的引用(堆中的实例)，对引用的操作也会反应到实例中
     * @param list
     */
    public static void test(List list){
        list.add("1");
        list.add("2");
        list.add("3");
    }

    /**
     * 如果java中是引用传递的话，则形参list指向了新创建的对象，输出结果应该为[1]，但实际输出结果为[]，strs还是指向了原来
     * 的对象，说明java中的对象传递是值传递
     * @param list
     */
    public static void test1(List list){
        list = new ArrayList();
        list.add("1");
    }

    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();
        ValTransferTest.test(strs);
        System.out.println(strs);
    }
}
