package org.itliu.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther itliu
 * @despription 注册式单例
 * 通过内部的容器保证单例
 * @data 2020/2/8
 */
public class Singleton09 {
    private static final Map<String, Object> _ioc = new ConcurrentHashMap<>();

    private Singleton09() {
    }

    public static Object getBean(String clazzName) throws Exception {
        if (_ioc.containsKey(clazzName)) {
            return _ioc.get(clazzName);
        } else {
            final Class<?> clazz = Class.forName(clazzName);
            return _ioc.put(clazzName, clazz.newInstance());
        }
    }
}
