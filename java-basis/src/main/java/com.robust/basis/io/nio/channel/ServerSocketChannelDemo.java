package com.robust.basis.io.nio.channel;

import com.robust.basis.io.nio.buffer.Buffers;
import com.robust.tools.kit.concurrent.ThreadUtil;
import com.robust.tools.kit.io.IOUtil;
import com.robust.tools.kit.text.Charsets;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description: 服务器端，:接收客户端发送过来的数据并显示，服务器把上接收到的数据加上"echo from service:"再发送回去
 * @Author: robust
 * @CreateDate: 2019/8/22 17:52
 * @Version: 1.0
 */
public class ServerSocketChannelDemo {

    public static class TCPEchoServer implements Runnable {

        /*服务器地址*/
        private InetSocketAddress inetAddress;

        public TCPEchoServer(int port) {
            inetAddress = new InetSocketAddress(port);
        }

        @Override
        public void run() {
            ServerSocketChannel serverSocketChannel = null;
            Selector selector = null;

            try {
                //创建选择器
                selector = Selector.open();
                //创建服务器通道
                serverSocketChannel = ServerSocketChannel.open();
                //设置为非阻塞
                serverSocketChannel.configureBlocking(false);
                //设置监听服务器的端口, 设置最大连接缓冲数为100
                serverSocketChannel.bind(inetAddress, 100);
                //服务器通道只对TCP连接事件感兴趣
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("server started with address:" + inetAddress);

            //服务器线程被中断后会退出
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    int n = selector.select();
                    if (n == 0) {
                        continue;
                    }

                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    SelectionKey key = null;

                    while (iterator.hasNext()) {
                        key = iterator.next();
                        //防止下次selector返回已处理过的通道
                        iterator.remove();
                        /*若发现异常,说明客户端连接出现问题,但服务器要保持正常*/
                        try {
                            /*server通道只对连接事件感兴趣*/
                            if (key.isAcceptable()) {
                                /*accept方法会返回一个普通通道,每个通道在内核中都对应一个socket缓冲区*/
                                SocketChannel sc = serverSocketChannel.accept();
                                sc.configureBlocking(false);

                                /*向选择器注册这个通道和普通通道感兴趣的事件，同时提供这个新通道相关的缓冲区*/
                                sc.register(selector, SelectionKey.OP_READ, new Buffers(256,256));

                                System.out.println("accept from " + sc.getRemoteAddress());
                            }

                            /*普通通道感兴趣读事件且有数据可读*/
                            if(key.isReadable()) {
                                /*通过SelectionKey获取通道对应的缓冲区*/
                                Buffers buffers = (Buffers) key.attachment();
                                ByteBuffer readBuffer = buffers.getReadBuffer();
                                ByteBuffer writeBuffer = buffers.getWriteBuffer();

                                /*通过SelectionKey获取对应的通道*/
                                SocketChannel channel = (SocketChannel) key.channel();
                                /*从底层socket读缓冲区中读入数据*/
                                channel.read(readBuffer);
                                readBuffer.flip();

                                /*解码显示客户端发送来的消息*/
                                CharBuffer decode = Charsets.UTF_8.decode(readBuffer);
                                System.out.println(decode.array());

                                readBuffer.rewind();

                                /*准备好向客户端发送消息*/
                                writeBuffer.put("echo from client: ".getBytes(Charsets.UTF_8));
                                writeBuffer.put(readBuffer);

                                readBuffer.clear();
                                /*设置通道写事件*/
                                key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
                            }

                            /*通道感兴趣写事件且底层缓冲区有空闲*/
                            if(key.isWritable()) {
                                Buffers buffers = (Buffers) key.attachment();
                                ByteBuffer writeBuffer = buffers.getWriteBuffer();
                                writeBuffer.flip();

                                SocketChannel sc = (SocketChannel) key.channel();
                                int len = 0;
                                while(writeBuffer.hasRemaining()) {
                                    len = sc.write(writeBuffer);
                                    /*说明底层的缓冲区已满*/
                                    if(len == 0) {
                                        break;
                                    }
                                }

                                writeBuffer.compact();

                                /*说明数据全部写入到底层的socket缓冲区*/
                                if(len != 0) {
                                    /*取消通道的写事件*/
                                    key.interestOps(key.interestOps() & (~SelectionKey.OP_WRITE));
                                }
                            }
                        } catch (Exception e){
                            System.out.println("server encounter client error");
                            /*若客户端连接出现异常，从Selector中移除这个key*/
                            key.cancel();
                            key.channel().close();
                        }

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtil.closeQuietly(selector);
            }
        }
    }

    public static void main(String[] args) {
        TCPEchoServer server = new TCPEchoServer(8080);
        Thread thread = new Thread(server);
        thread.start();

        ThreadUtil.sleep(10000);
        //结束服务器线程
        thread.interrupt();
    }
}
