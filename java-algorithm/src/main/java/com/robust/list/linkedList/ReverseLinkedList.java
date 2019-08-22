package com.robust.list.linkedList;

import java.util.LinkedList;

/**
 * 反转单链表
 */
public class ReverseLinkedList {
    public static void reverse(LinkedList list) {
        for(int i = 0, mid = list.size() >> 1, j = list.size() - 1; i < mid;i++,j--) {
            list.set(i, list.set(j, list.get(i)));
        }
    }

    /*public static void reverse1(LinkedList list) {
        ListIterator fwd = list.listIterator();
        ListIterator rev = list.listIterator(list.size());
        for (int i=0, mid=list.size()>>1; i<mid; i++) {
            Object tmp = fwd.next();
            fwd.set(rev.previous());
            rev.set(tmp);
        }
    }*/
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        /*System.out.println(list);

        Collections.reverse(list);
        System.out.println(list);*/
        System.out.println(list);
        reverse(list);
        System.out.println(list);
    }
}
