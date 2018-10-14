package com.robust.factory.simple;

import com.robust.factory.Product;
import com.robust.factory.ProductA;
import com.robust.factory.ProductB;

/**
 * @author bintao
 * @Despription
 * @date 2018/8/26 21:09
 */
public class SimpleFactory {
    public static Product getProduct(String type) {
        if("A".equals(type)) {
            return new ProductA();
        } else if("B".equals(type)) {
            return new ProductB();
        } else {
            System.out.println("没有您想要的产品");
        }
        return null;
    }
}
