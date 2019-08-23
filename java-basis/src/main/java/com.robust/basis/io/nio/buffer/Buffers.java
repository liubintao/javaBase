package com.robust.basis.io.nio.buffer;

import lombok.Getter;

import java.nio.ByteBuffer;

/**
 * @Description: 自定义Buffer类中包含读缓冲区和写缓冲区,用于注册通道时的附加对象
 * @Author: robust
 * @CreateDate: 2019/8/23 15:13
 * @Version: 1.0
 */
@Getter
public class Buffers {

    ByteBuffer readBuffer;
    ByteBuffer writeBuffer;

    public Buffers(int readCapacity, int writeCapacity) {
        this.readBuffer = ByteBuffer.allocate(readCapacity);
        this.writeBuffer = ByteBuffer.allocate(writeCapacity);
    }
}
