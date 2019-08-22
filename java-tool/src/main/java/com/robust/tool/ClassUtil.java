package com.robust.tool;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/1/22 10:08
 * @Version: 1.0
 */
public abstract class ClassUtil {

    private static final Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new IdentityHashMap<>(8);
    private static final Map<Class<?>, Class<?>> primitiveTypeToWrapperMap = new IdentityHashMap<>(8);
    private static final Map<String, Class<?>> primitiveTypeNameMap = new HashMap<>(32);

    private static final Map<String, Class<?>> commonClassCache = new HashMap<>(64);
//    private static final Set<Class<?>> javaLanguageInterfaces;

    static {
        primitiveWrapperTypeMap.put(Boolean.class, boolean.class);
        primitiveWrapperTypeMap.put(Character.class, char.class);
        primitiveWrapperTypeMap.put(Byte.class, byte.class);
        primitiveWrapperTypeMap.put(Short.class, short.class);
        primitiveWrapperTypeMap.put(Integer.class, int.class);
        primitiveWrapperTypeMap.put(Long.class, long.class);
        primitiveWrapperTypeMap.put(Double.class, double.class);
        primitiveWrapperTypeMap.put(Float.class, float.class);

        primitiveWrapperTypeMap.entrySet().forEach(entry -> {
            primitiveTypeToWrapperMap.put(entry.getValue(), entry.getKey());
            registerCommonClass(entry.getKey());
        });
    }

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        if(cl == null) {
            try {
                cl = ClassUtil.class.getClassLoader();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cl;
    }

    /***
     * 将给定的class注册到工具类缓存中
     * @param commonClasses
     */
    private static void registerCommonClass(Class<?>... commonClasses) {
        Stream.of(commonClasses).forEach(clz -> {
            commonClassCache.put(clz.getName(), clz);
        });
    }
}
