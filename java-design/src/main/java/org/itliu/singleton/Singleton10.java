package org.itliu.singleton;

import java.io.*;

/**
 * @author bintao
 * @Despription 序列化就是说把内存中的状态通过转换成字节码的形式，从而转换一个IO流，写入到其他地方(可以是磁盘，网络IO)
 * 内存中状态给永久保存下来了
 * 反序列化-将已经持久化的字节码内容，转换为IO流，通过IO流的读取，进而将读取的内容转换为Java对象，在转换
 * 过程中会重新创建对象new
 * @date 2018/9/1 14:51
 */

/**
 * @auther itliu
 * @despription 序列化就是说把内存中的状态通过转换成字节码的形式，从而转换一个IO流，写入到其他地方(可以是磁盘，网络IO)
 *              内存中状态给永久保存下来了
 *              反序列化-将已经持久化的字节码内容，转换为IO流，通过IO流的读取，进而将读取的内容转换为Java对象，在转换
 *              过程中会重新创建对象new
 * 通过内部的容器保证单例
 * @data 2020/2/8
 */
public class Singleton10 implements Serializable {
    private Singleton10() {
    }

    public static final Singleton10 INSTANCE = new Singleton10();

    public static Singleton10 getInstance() {
        return INSTANCE;
    }

    /** 如果被反序列化的对象的类存在readResolve这个方法，他会调用这个方法来返回一个“array”，然后浅拷贝一份，作为返回值，
     *  并且无视掉反序列化的值，即使那个字节码已经被解析。
     */
    private Object readResolve() {
        return INSTANCE;
    }

    public static void main(String[] args) throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("obj.data"));
        objectOutputStream.writeObject(Singleton10.getInstance());

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("obj.data"));
        Singleton10 instance = (Singleton10) inputStream.readObject();
        System.out.println(instance == Singleton10.getInstance());
    }
}
