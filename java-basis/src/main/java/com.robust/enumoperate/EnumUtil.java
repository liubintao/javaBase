package com.robust.enumoperate;

import com.robust.utils.StringUtil;

import java.lang.reflect.Method;

/**
 * @author bintao
 * @Despription
 * @date 2018/4/16 14:29
 */
public class EnumUtil {

    public static <T extends Enum<?>> T getByCode(Class<T> enumClz, String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }
        T[] enumArray = enumClz.getEnumConstants();
        for (int i = 0, j = enumArray.length; i < j; i++) {
            T t = enumArray[i];
            Method method;
            String ret;
            try {
                method = enumClz.getMethod("getCode");
                ret = (String) method.invoke(t);
                if (code.equals(ret)) {
                    return t;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        FileType fileType = EnumUtil.getByCode(FileType.class, "01");
        System.out.println(fileType.getDesc());
    }
}
