package com.robust.basis.newspecific.java7;

import java.io.IOException;

public class ExceptionTest {
    public static void test() throws IOException {


        try {
            System.out.println(1/0);
            throw new IOException();
        } catch (final Exception e) {
            throw e;
        }
    }

    public static void main(String[] args) throws IOException {
        test();
    }
}
