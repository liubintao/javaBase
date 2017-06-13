package com.robust.singleton;

/**
 * Created by liubintao on 2017/6/12.
 */
public class Singleton {

    //通过volatile关键字来确保安全
    private volatile static Singleton singleton;

    private Singleton(){}

    public static Singleton getInstance(){
        if(singleton == null){
            synchronized (Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
