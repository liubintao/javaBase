package com.robust.singleton.hungry;

/**
 * @author bintao
 * @Despription 饿汉式单例，绝对线程安全
 *                  优点：没有加任何的锁，执行效率比较高，在用户体验上来说，比懒汉式更好
 *                  缺点：类加载的时候就初始化，不管你用还是不用，我都占用着空间，浪费了内存，有可能占着茅坑不拉屎
 * @date 2018/9/1 9:41
 */
public class Hungry {
    /**
     * 在类加载的时候就立即初始化，并且创建单例对象
     */
    private static final Hungry hungry = new Hungry();

    private Hungry(){}

    /**
     *
     * @return
     */
    public static Hungry getInstance() {
        return hungry;
    }
}
