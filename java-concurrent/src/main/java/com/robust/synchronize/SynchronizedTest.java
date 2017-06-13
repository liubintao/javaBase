package com.robust.synchronize;

/**
 * Created by liubintao on 2017/6/1.
 */
public class SynchronizedTest {

    public synchronized void test1(){

    }

    public void test2(){
        synchronized (this){

        }
    }
}
