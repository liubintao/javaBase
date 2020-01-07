package com.itliu.algorithm.linkedList;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/12/24 11:57
 * @Version: 1.0
 */
public class PalindromeLinkedListTest {

    @Test
    public void isPalindrome() {
        ListNode node1 = new ListNode(1, "1");
        ListNode node2 = new ListNode(2, "2");
        ListNode node3 = new ListNode(2, "3");
        ListNode node4 = new ListNode(1, "4");

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println(PalindromeLinkedList.isPalindrome(node1));
    }
}