package com.robust.singleton.lazy;

/**
 * @author bintao
 * @Despription 懒汉式单例，在外部使用的时候才初始化
 * @date 2018/9/1 9:48
 */
public class LazyOne {

    private LazyOne(){}
    private static LazyOne lazyLoad = null;

    public static LazyOne getInstance() {
        if(lazyLoad == null) {
            lazyLoad = new LazyOne();
        }
        return lazyLoad;
    }
}
