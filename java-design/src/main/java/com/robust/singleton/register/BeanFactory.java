package com.robust.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bintao
 * @Despription
 * @date 2018/9/1 12:30
 */
public class BeanFactory {
    private BeanFactory(){}

    private static Map<String, Object> ioc = new ConcurrentHashMap<>();

    public static Object getBean(String className){
        if(ioc.containsKey(className)) {
            return ioc.get(className);
        } else {
            Class clz = null;
            try {
                Object obj = Class.forName(className).newInstance();
                return ioc.put(className, obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
