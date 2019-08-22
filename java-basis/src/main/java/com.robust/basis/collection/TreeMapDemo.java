package com.robust.basis.collection;

import java.util.TreeMap;

/**
 * Created by bintao on 2017/8/17.
 */
public class TreeMapDemo {
    public static void main(String[] args) {
        // creating tree map
        TreeMap<Integer, String> treemap = new TreeMap<>();

        // populating tree map
        treemap.put(2, "two");
        treemap.put(1, "one");
        treemap.put(3, "three");
        treemap.put(6, "six");
        treemap.put(5, "five");

        System.out.println("Checking floor entry for 6");
        System.out.println("Value is: "+ treemap.floorEntry(5));
        treemap.remove(5);
        System.out.println(treemap);
    }
}
