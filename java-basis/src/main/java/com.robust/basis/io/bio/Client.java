package com.robust.basis.io.bio;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/7/30 10:41
 * @Version: 1.0
 */
public class Client {
    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            BioServer server = new BioServer();
            try {
                server.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


        TimeUnit.SECONDS.sleep(1);

        final Random random = new Random(System.currentTimeMillis());
        new Thread(() -> {
            while (true) {
                BioClient.send("hi:" + random.nextInt(100));
            }

        }).start();
    }
}
