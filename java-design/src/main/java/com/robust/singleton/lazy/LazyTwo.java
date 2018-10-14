package com.robust.singleton.lazy;

/**
 * @author bintao
 * @Despription
 * @date 2018/9/1 11:27
 */
public class LazyTwo {
    private LazyTwo() {}

    private static LazyTwo lazy = null;

    public static synchronized LazyTwo getInstance() {
        if(lazy == null) {
            lazy = new LazyTwo();
        }
        return lazy;
    }
}
