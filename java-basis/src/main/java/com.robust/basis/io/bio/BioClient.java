package com.robust.basis.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/7/30 10:35
 * @Version: 1.0
 */
public class BioClient {
    private static final int PORT = 8888;
    private static final String IP = "127.0.0.1";

    public static void send(String content) {
        send(PORT, content);
    }

    private static void send(int port, String content) {
        Socket socket = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            socket = new Socket(IP, port);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println(content);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(br.readLine());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                br = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.close();
            try {
                socket.close();
                socket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
