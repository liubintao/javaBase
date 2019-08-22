package com.robust.basis.io.nio.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/8/2 9:39
 * @Version: 1.0
 */
public class NIOServer {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private static final int PORT = 8080;

    public NIOServer() throws IOException {
        //打开Server Socket Channel
        serverSocketChannel = ServerSocketChannel.open();
        //配置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //绑定Server port
        serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
        //创建Selector
        selector = Selector.open();
        //注册ServerSocketChannel到Selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端启动完成");

        handlerKeys();
    }

    private void handlerKeys() throws IOException {
        for (; ; ) {
            //通过Selector选择Channel
            int selectNum = selector.select(30 * 1000L);
            if (selectNum == 0) {
                continue;
            }
            System.out.println("选择 Channel 数量：" + selectNum);
            //可选择Channel的SelectionKey集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                //移除下面要处理的selectionKey
                iterator.remove();
                //忽略无效的SelectionKey
                if (!selectionKey.isValid()) {
                    continue;
                }
                handlerKey(selectionKey);
            }
        }
    }

    private void handlerKey(SelectionKey selectionKey) throws IOException {
        //接受连接就绪
        if (selectionKey.isAcceptable()) {
            handlerAcceptableKey(selectionKey);
        }
        //读就绪
        if (selectionKey.isReadable()) {
            handlerReadableKey(selectionKey);
        }
        //写就绪
        if (selectionKey.isWritable()) {
            handlerWritableKey(selectionKey);
        }
    }

    private void handlerAcceptableKey(SelectionKey selectionKey) throws IOException {
        //接受Client Channel Socket
        SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
        //配置为非阻塞
        socketChannel.configureBlocking(false);
        System.out.println("接受新的Channel");
        //注册Client Channel 到 Selector
        socketChannel.register(selector, SelectionKey.OP_READ, new ArrayList<>());
    }

    private void handlerReadableKey(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        //读取数据
        ByteBuffer readBuffer = CodecUtil.read(socketChannel);
        //处理连接已经断开的情况
        if (readBuffer == null) {
            System.out.println("连接已断开");
            socketChannel.register(selector, 0);
            return;
        }
        //打印数据
        if (readBuffer.position() > 0) {//写入模式下
            String content = CodecUtil.newString(readBuffer);
            System.out.println("读取到数据: " + content);

            //添加到响应队列
            List<String> responseQueue = (List<String>) selectionKey.attachment();
            responseQueue.add("响应：" + content);
            socketChannel.register(selector, SelectionKey.OP_WRITE, responseQueue);
        }
    }

    private void handlerWritableKey(SelectionKey selectionKey) throws ClosedChannelException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        //遍历响应队列
        List<String> responseQueue = (List<String>) selectionKey.attachment();
        for(String content:responseQueue) {
            System.out.println("写入数据：" + content);
            //返回
            CodecUtil.write(socketChannel, content);
        }
        responseQueue.clear();
        //注册 Client Socket Channel 到 Selector
        socketChannel.register(selector, SelectionKey.OP_READ, responseQueue);
    }

    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer();
    }
}
