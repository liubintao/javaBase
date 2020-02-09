package org.itliu.singleton;

/**
 * @auther itliu
 * @despription 饿汉式单例，绝对线程安全，由JVM保证(类加载到内存后，就实例化一个单例)
 * 优点：没有加任何的锁，执行效率比较高，在用户体验上来说，比懒汉式更好
 * 缺点：类加载的时候就初始化，不管你用还是不用，我都占用着空间，浪费了内存，有可能占着茅坑不拉屎
 * @data 2020/2/8
 */
public class Singleton01 {
    /**
     * 在类加载的时候就立即初始化，并且创建单例对象
     */
    private static final Singleton01 _instance = new Singleton01();

    /**
     * 重点，构造函数私有化，防止类在外部被创建
     */
    private Singleton01() {
    }

    public static final Singleton01 getInstance() {
        return _instance;
    }

    public static void main(String[] args) {
        System.out.println(Singleton01.getInstance().hashCode());
        System.out.println(Singleton01.getInstance().hashCode());
    }
}
