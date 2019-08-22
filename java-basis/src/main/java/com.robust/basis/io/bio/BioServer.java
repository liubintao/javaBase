package com.robust.basis.io.bio;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/7/30 9:17
 * @Version: 1.0
 */
public class BioServer {
    private static final int DEFAULT_SERVER_PORT = 8888;
    private static ServerSocket serverSocket = null;

    public static int getPort() {
        return DEFAULT_SERVER_PORT;
    }

    public static void start() throws Exception {
        start(DEFAULT_SERVER_PORT);

    }

    private synchronized static void start(int port) throws IOException {
        if(serverSocket != null) {
            return;
        }
        try {
            serverSocket = new ServerSocket(DEFAULT_SERVER_PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        } finally {
            serverSocket.close();
            serverSocket = null;
        }
    }

}
