package com.robust.factory.function;

import com.robust.factory.Product;
import com.robust.factory.ProductB;

/**
 * @author bintao
 * @Despription
 * @date 2018/8/26 21:17
 */
public class ProductBFactory implements Factory {
    @Override
    public Product getProduct() {
        return new ProductB();
    }
}
