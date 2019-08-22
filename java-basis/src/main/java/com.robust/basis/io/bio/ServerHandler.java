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
 * @CreateDate: 2019/7/30 10:24
 * @Version: 1.0
 */
public class ServerHandler implements Runnable {
    private Socket socket;
    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;

        String content;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);

            System.out.println(content = br.readLine());
            pw.println("receive:" + content);
            TimeUnit.SECONDS.sleep(1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                br = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.close();
            pw = null;
        }
    }
}
