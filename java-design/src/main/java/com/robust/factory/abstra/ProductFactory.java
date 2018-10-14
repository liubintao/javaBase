package com.robust.factory.abstra;

import com.robust.factory.Product;
import com.robust.factory.ProductA;
import com.robust.factory.ProductB;

/**
 * @author bintao
 * @Despription
 * @date 2018/8/26 21:22
 */
public class ProductFactory extends AbstractFactory {
    @Override
    public Product getProductA() {
        return new ProductA();
    }

    @Override
    public Product getProductB() {
        return new ProductB();
    }
}
