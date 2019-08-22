package com.robust.concurrency.thread;

import javafx.concurrent.Task;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/4/23 14:53
 * @Version: 1.0
 */
public class TaskRunnable implements Runnable{
    BlockingQueue<Task> queue = new ArrayBlockingQueue<Task>(10);

    public void run(){
        try{
//            processTask(queue.take());
            queue.take();
        }catch(InterruptedException e){
//恢复被中断的状态
            Thread.currentThread().interrupt();
        }
    }
}