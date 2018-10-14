package com.robust.factory.function;

import com.robust.factory.Product;

/**
 * @author bintao
 * @Despription
 * @date 2018/8/26 21:05
 */
public interface Factory {
    /**
     * 获取产品
     * @return
     */
    Product getProduct();
}
