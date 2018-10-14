package com.robust.singleton.lazy;

/**
 * @author bintao
 * @Despription 这种形式兼顾懒汉式的内存浪费，也兼顾Synchronized的性能问题，完美的屏蔽了这两个缺点
 * @date 2018/9/1 11:42
 */
public class LazyThree {
    private static boolean initialized = false;

    /**
     * 默认使用LazyThree的时候，会先初始化内部类，如果没有使用的话，内部类则不加载
     * 内部类只有在外部类调用的时候才会被加载，内部类一定是要在方法调用之前初始化
     */
    private LazyThree() {
        /**
         * 防止反射入侵
         */
        synchronized (this) {
            if(initialized == false) {
                initialized = !initialized;
            } else {
                throw new RuntimeException("单例已被侵犯");
            }
        }
    }

    /**
     * 每一个关键字都不是多余的
     * static 是为了使单例的空间能够共享
     * final 保证方法不会被重写,重载
     *
     * @return
     */
    public static final LazyThree getInstance(){
        /**
         * 再返回结果之前，内部类一定会先加载
         */
        return LazyHolder.LAZY;
    }

    /**
     * 默认不加载
     */
    private static class LazyHolder{
        private static final LazyThree LAZY = new LazyThree();
    }
}
