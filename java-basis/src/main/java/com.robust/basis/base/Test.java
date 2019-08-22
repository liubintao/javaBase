package com.robust.basis.base;

public class Test extends Number {

    public static void main(String[] args) {
        int a = 10;
        for (; ; ) {
            if (a > 5) {
                System.out.println(a--);
            } else {
                return;
            }
        }
        /*do {
            System.out.println(a--);
        } while (a > 5);*/
    }
}
