package com.itliu.algorithm.linkedList;

import java.util.LinkedList;

/**
 * 反转单链表
 */
public class ReverseLinkedList {
    /**
     * 使用java API实现
     *
     * @param list
     */
    public static void reverse(LinkedList list) {
        for (int i = 0, mid = list.size() >> 1, j = list.size() - 1; i < mid; i++, j--) {
            list.set(i, list.set(j, list.get(i)));
        }
    }

    /**
     * 使用迭代方式实现：
     * <p>
     * 在遍历列表时，将当前节点的 next 指针改为指向前一个元素。
     * 由于节点没有引用其上一个节点，因此必须事先存储其前一个元素。
     * 在更改引用之前，还需要另一个指针来存储下一个节点。不要忘记在最后返回新的头引用！
     *
     * @param head 头结点
     * @return 新的头结点
     */
    public static ListNode reverseByIteration(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
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

    }


    public static void out(ListNode listNode) {
        StringBuilder sb = new StringBuilder();
        while (listNode != null) {
            sb.append(listNode.data + " ");
            listNode = listNode.next;
        }
        System.out.println(sb.toString());
    }
}
