package com.robust.basis.io.nio2.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

public class NetworkChannelTest {

    public static void test() throws IOException {
        SelectorProvider provider = SelectorProvider.provider();
        SocketChannel socketChannel = provider.openSocketChannel();
        SocketAddress address = new InetSocketAddress(3080);
        socketChannel.bind(address);
        Set<SocketOption<?>> options = socketChannel.supportedOptions();
        System.out.println(options.toString());
        /**设置套接字的ToS(服务条款)选项*/
        socketChannel.setOption(StandardSocketOptions.IP_TOS, 3);
        Boolean keepAlive = socketChannel.getOption(StandardSocketOptions.SO_KEEPALIVE);
        System.out.println(keepAlive);
        System.out.println(socketChannel.getOption(StandardSocketOptions.IP_TOS));
    }

    public static void main(String[] args) throws IOException {
        test();
    }
}
