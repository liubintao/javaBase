package com.robust.concurrency.thread.visibility;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/4/16 11:44
 * @Version: 1.0
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;
    private static class ReaderThread extends Thread{
        public void run(){
            while(!ready)
            Thread.yield();
            System.out.println(number);
        }
    }
    public static void main(String[]args){
        /*for(int i = 0; i < 10000; i++) {

        }*/
        new ReaderThread().start();
        number=42;
        ready=true;

    }
}
