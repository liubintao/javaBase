package com.robust.basis.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author bintao
 * @Despription
 * @date 2018/4/16 14:42
 */
public class StringUtil {
    public static boolean isNotBlank(String str) {
        return StringUtils.isNotBlank(str);
    }

    public static boolean isBlank(String str){
        return StringUtils.isBlank(str);
    }
}
