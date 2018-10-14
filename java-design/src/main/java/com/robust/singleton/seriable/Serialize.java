package com.robust.singleton.seriable;

import java.io.Serializable;

/**
 * @author bintao
 * @Despription 序列化就是说把内存中的状态通过转换成字节码的形式，从而转换一个IO流，写入到其他地方(可以是磁盘，网络IO)
 *                  内存中状态给永久保存下来了
 *                反序列化-将已经持久化的字节码内容，转换为IO流，通过IO流的读取，进而将读取的内容转换为Java对象，在转换
 *                过程中会重新创建对象new
 * @date 2018/9/1 14:51
 */
public class Serialize implements Serializable {
    private Serialize(){}
    public static final Serialize INSTANCE = new Serialize();

    public static Serialize getInstance() {
        return INSTANCE;
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
