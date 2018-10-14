package com.robust.utils;

import java.util.Iterator;
import java.util.Map;

/**
 * @Description Map工具类
 * @author bintao
 * @date 2018年5月31日 下午3:30:13
 */
public class MapUtil {

    /**
     * 
     * @Description 合并两个Map，当源Map中的key存在于目标Map时，默认不会覆盖
     * @param src
     * @param target
     */
    @SuppressWarnings("rawtypes")
    public static void merge(Map src, Map target) {
        merge(src, target, false);
    }

    /**
     * 
     * @Description
     * @param src
     * @param target
     * @param cover 当源Map中的key存在于目标Map时，是否覆盖
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void merge(Map src, Map target, boolean cover) {
        Iterator<Map.Entry> iterator = src.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            Object key = entry.getKey();
            if (target.containsKey(key)) {
                if (cover) {
                    target.put(key, entry.getValue());
                }
            }
            target.put(key, entry.getValue());
        }
    }
}
