package com.robust.basis.io.nio.channel;

import com.robust.basis.io.nio.buffer.Buffers;
import com.robust.tools.kit.concurrent.ThreadUtil;
import com.robust.tools.kit.io.IOUtil;
import com.robust.tools.kit.number.RandomUtil;
import com.robust.tools.kit.text.Charsets;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description: 客户端，每隔1-20秒自动向服务器发送数据，接收服务器收到数据并显示
 * @Author: robust
 * @CreateDate: 2019/8/23 16:08
 * @Version: 1.0
 */
public class ClientSocketChannelDemo {

    private static class TcpEchoClient implements Runnable {

        /*客户端线程名*/
        private String name;
        /*服务器的ip地址和端口*/
        private InetSocketAddress address;

        public TcpEchoClient(String name, InetSocketAddress address) {
            this.name = name;
            this.address = address;
        }

        @Override
        public void run() {
            Selector selector = null;

            try {
                /*创建TCP通道*/
                SocketChannel socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(false);

                /*创建选择器*/
                selector = selector.open();
                /*注册通道感兴趣事件*/
                socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                /*向服务器发起连接, 一条通道代表一条TCP连接*/
                socketChannel.connect(address);

                while (!socketChannel.finishConnect()) {

                }
                System.out.println(name + " " + "finished connection");
            } catch (IOException e) {
                System.out.println("client connect failed");
                return;
            }

            /*与服务器断开或线程被中断则结束线程*/
            try {
                int i = 1;
                while (!Thread.currentThread().isInterrupted()) {
                    //阻塞等待
                    selector.select();

                    //set中的每个key代表一个通道
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();

                        /*防止下次select方法返回已处理过的通道*/
                        iterator.remove();

                        /*通过SelectionKey获取对应的通道*/
                        Buffers buffers = (Buffers) key.attachment();
                        ByteBuffer readBuffer = buffers.getReadBuffer();
                        ByteBuffer writeBuffer = buffers.getWriteBuffer();

                        /*通过SelectionKey获取通道对应的缓冲区*/
                        SocketChannel channel = (SocketChannel) key.channel();
                        /*表示底层socket的读缓冲区有数据可读*/
                        if(key.isReadable()) {
                            /*从socket的读缓冲区读取到程序定义的缓冲区中*/
                            channel.read(readBuffer);
                            readBuffer.flip();

                            /*字节到utf8解码*/
                            CharBuffer charBuffer = Charsets.UTF_8.decode(readBuffer);
                            System.out.println(charBuffer);
                            readBuffer.clear();
                        }

                        /*表示底层socket的写缓冲区可写*/
                        if(key.isWritable()) {
                            writeBuffer.put((name + " " + i).getBytes(Charsets.UTF_8));
                            writeBuffer.flip();
                            /*将程序定义的缓冲区中的内容写入到socket的写缓冲区中*/
                            channel.write(writeBuffer);
                            writeBuffer.clear();
                            i++;
                        }
                    }
                    ThreadUtil.sleep(RandomUtil.nextInt(1000, 20000));
                }
            } catch (Exception e) {

            } finally {
                IOUtil.closeQuietly(selector);
            }
        }
    }

    public static void main(String[] args) {
        InetSocketAddress address = new InetSocketAddress(8080);
        Thread threadA = new Thread(new TcpEchoClient("thread a", address));
        Thread threadB = new Thread(new TcpEchoClient("thread b", address));
        Thread threadC = new Thread(new TcpEchoClient("thread c", address));
        Thread threadD = new Thread(new TcpEchoClient("thread d", address));

        threadA.start();
        threadB.start();
        threadC.start();

        ThreadUtil.sleep(5000);

        //结束客户端
        threadA.interrupt();

        threadD.start();
    }
}
