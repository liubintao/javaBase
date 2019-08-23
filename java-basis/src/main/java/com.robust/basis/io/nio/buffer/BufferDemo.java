package com.robust.basis.io.nio.buffer;

import com.robust.tools.kit.text.Charsets;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Arrays;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/8/22 17:36
 * @Version: 1.0
 */
public class BufferDemo {

    public static void decode(String str) {
        ByteBuffer buffer = ByteBuffer.allocate(128);
        buffer.put(str.getBytes(Charsets.UTF_8));
        buffer.flip();

        /*对bytebuffer中的内容解码*/
        CharBuffer charBuffer = Charsets.UTF_8.decode(buffer);
        /*array()返回的就是内部的数组引用，解码以后的有效长度是0~limit*/
        char[] chars = Arrays.copyOf(charBuffer.array(), charBuffer.limit());
        System.out.println(Arrays.toString(chars));
    }

    public static void encode(String str) {
        CharBuffer charBuffer = CharBuffer.allocate(128);
        charBuffer.put(str);
        charBuffer.flip();

        ByteBuffer buffer = Charsets.UTF_8.encode(charBuffer);
        /*array()返回的就是内部的数组引用，编码以后的有效长度是0~limit*/
        byte[] bytes = Arrays.copyOf(buffer.array(), buffer.limit());
        System.out.println(Arrays.toString(bytes));
    }

    public static void main(String[] args) {
        String a = "张三";
        encode(a);
        decode(a);
    }
}
