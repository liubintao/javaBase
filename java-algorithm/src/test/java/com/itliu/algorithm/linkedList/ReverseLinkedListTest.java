package com.itliu.algorithm.linkedList;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/12/23 11:40
 * @Version: 1.0
 */
public class ReverseLinkedListTest {

    @Test
    public void reverse() {
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
        ReverseLinkedList.reverse(list);
        System.out.println(list);
    }

    @Test
    public void reverseByIteration() {
        ListNode<Integer> listNode1 = new ListNode<>(1);
        ListNode<Integer> listNode2 = new ListNode<>(2);
        ListNode<Integer> listNode3 = new ListNode<>(3);
        ListNode<Integer> listNode4 = new ListNode<>(4);
        ListNode<Integer> listNode5 = new ListNode<>(5);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        ReverseLinkedList.out(listNode1);
        ListNode newHead = ReverseLinkedList.reverseByIteration(listNode1);
        ReverseLinkedList.out(newHead);
    }
}