package com.robust.basis.io.nio;

import java.nio.CharBuffer;

/**
 * Created by bintao on 2017/8/28.
 */
public class BufferTest {

    public static void charBufferTest(){
        CharBuffer buffer = CharBuffer.allocate(8);
        String text = "apple";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            buffer.put(c);
        }
        System.out.println(buffer.capacity());

        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        buffer.flip();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.hasArray());

        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
        System.out.println(buffer.position());
    }
    public static void main(String[] args) {
        charBufferTest();

    }
}
