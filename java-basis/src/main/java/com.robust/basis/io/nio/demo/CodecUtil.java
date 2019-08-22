package com.robust.basis.io.nio.demo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Description: 编解码工具类
 * @Author: robust
 * @CreateDate: 2019/8/2 11:39
 * @Version: 1.0
 */
public class CodecUtil {

    public static ByteBuffer read(SocketChannel channel) {
        //注意，不考虑拆包的处理
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            int count = channel.read(buffer);
            if (count == -1) {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return buffer;
    }

    public static void write(SocketChannel channel, String content) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            buffer.put(content.getBytes("UTF8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        //写入channel
        buffer.flip();
        try {
            //注意：不考虑写入超过Channel缓存上限
            channel.write(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String newString(ByteBuffer buffer) {
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        System.arraycopy(buffer.array(), 0, bytes, 0, buffer.remaining());
        try {
            return new String(bytes, "UTF8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
