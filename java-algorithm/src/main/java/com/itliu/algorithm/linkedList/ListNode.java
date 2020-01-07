package com.itliu.algorithm.linkedList;

/**
 * @Description: 链表演示对象
 * @Author: robust
 * @CreateDate: 2019/12/24 11:20
 * @Version: 1.0
 */
public class ListNode<T> {
    public T data;
    public ListNode next;
    public String ext;

    public ListNode(T data, ListNode next) {
        this.data = data;
        this.next = next;
    }

    public ListNode(T data) {
        this.data = data;
    }

    public ListNode(T data, String ext) {
        this.data = data;
        this.ext = ext;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "data=" + data +
                ", ext='" + ext + '\'' +
                '}';
    }
}
