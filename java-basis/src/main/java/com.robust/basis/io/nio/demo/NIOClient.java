package com.robust.basis.io.nio.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/8/2 15:29
 * @Version: 1.0
 */
public class NIOClient {
    private SocketChannel socketChannel;
    private Selector selector;
    private final List<String> responseQueue = new ArrayList<>();

    private CountDownLatch connected = new CountDownLatch(1);

    public NIOClient() throws IOException, InterruptedException {
        //打开Client Socket Channel
        socketChannel = SocketChannel.open();
        //设置为非阻塞
        socketChannel.configureBlocking(false);
        //创建Selector
        selector = Selector.open();
        //注册Client Socket Channel到Selector上
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        //连接服务器
        socketChannel.connect(new InetSocketAddress(8080));

        new Thread(() -> {
            try {
                handlerKeys();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        if (connected.getCount() != 0) {
            connected.await();
        }

        System.out.println("Client 启动完成");
    }

    private void handlerKeys() throws IOException {
        for (; ; ) {

            // 通过 Selector 选择 Channel
            int selectNums = selector.select(30 * 1000L);
            if (selectNums == 0) {
                continue;
            }

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                //移除下面要处理的selectionKey
                iterator.remove();
                //忽略无效的selectionKey
                if (!selectionKey.isValid()) {
                    continue;
                }
                handlerKey(selectionKey);
            }
        }
    }

    private void handlerKey(SelectionKey selectionKey) throws IOException {
        //接受连接就绪
        if (selectionKey.isConnectable()) {
            handleConnectableKey(selectionKey);
        }

        //读就绪
        if (selectionKey.isReadable()) {
            handleReadableKey(selectionKey);
        }

        //写就绪
        if (selectionKey.isWritable()) {
            handleWritableKey(selectionKey);
        }
    }

    private void handleConnectableKey(SelectionKey selectionKey) throws IOException {
        //完成连接
        if (!socketChannel.isConnectionPending()) {
            return;
        }
        socketChannel.finishConnect();
        System.out.println("接受新的Channel");
        //注册Client Socket Channel到Selector
        socketChannel.register(selector, SelectionKey.OP_READ, responseQueue);
        //标记为已连接
        connected.countDown();
    }

    private void handleReadableKey(SelectionKey selectionKey) {
        // Client Socket Channel
        SocketChannel clientSocketChannel = (SocketChannel) selectionKey.channel();
        // 读取数据
        ByteBuffer readBuffer = CodecUtil.read(clientSocketChannel);
        // 打印数据
        if (readBuffer.position() > 0) { // 写入模式下，
            String content = CodecUtil.newString(readBuffer);
            System.out.println("读取数据：" + content);
        }
    }

    private void handleWritableKey(SelectionKey selectionKey) throws IOException {
        // Client Socket Channel
        SocketChannel clientSocketChannel = (SocketChannel) selectionKey.channel();

        // 遍历响应队列
        List<String> responseQueue = (ArrayList<String>) selectionKey.attachment();
        for (String content : responseQueue) {
            // 打印数据
            System.out.println("写入数据：" + content);
            // 返回
            CodecUtil.write(clientSocketChannel, content);
        }
        responseQueue.clear();

        // 注册 Client Socket Channel 到 Selector
        clientSocketChannel.register(selector, SelectionKey.OP_READ, responseQueue);
    }

    public synchronized void send(String content) throws ClosedChannelException {
        // 添加到响应队列
        responseQueue.add(content);
        // 打印数据
        System.out.println("写入数据：" + content);
        // 注册 Client Socket Channel 到 Selector
        socketChannel.register(selector, SelectionKey.OP_WRITE, responseQueue);
        selector.wakeup();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NIOClient client = new NIOClient();
        for (int i = 0; i < 30; i++) {
            client.send("nihao: " + i);
            Thread.sleep(1000L);
        }
    }
}
