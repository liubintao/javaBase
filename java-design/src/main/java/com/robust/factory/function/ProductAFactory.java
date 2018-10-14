package com.robust.factory.function;

import com.robust.factory.Product;
import com.robust.factory.ProductA;

/**
 * @author bintao
 * @Despription
 * @date 2018/8/26 21:15
 */
public class ProductAFactory implements Factory {
    @Override
    public Product getProduct() {
        return new ProductA();
    }
}
