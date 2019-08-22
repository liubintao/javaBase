package com.robust.queue;

import java.util.Stack;

/**
 * 使用栈实现队列的先入先出
 */
public class QueueByStack {

    static class Queue<T>{
        private Stack input = new Stack<>();
        private Stack output = new Stack<>();

        public <T> void push(T param) {
            input.push(param);
        }

        public T pop() {
            copy();
            return (T) output.pop();
        }

        public T peek() {
            copy();
            return (T) output.peek();
        }

        private void copy() {
            if(output.empty()) {
                while (!input.empty()) {
                    output.push(input.pop());
                }
            }
        }
    }

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.push(1);
        queue.push(2);
        queue.push(3);

        System.out.println(queue);


        System.out.println(queue.peek());
    }
}
