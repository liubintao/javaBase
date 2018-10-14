package com.robust.factory.abstra;

/**
 * @author bintao
 * @Despription
 * @date 2018/8/26 21:23
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        ProductFactory factory = new ProductFactory();
        System.out.println(factory.getProductA().getName());
        System.out.println(factory.getProductB().getName());
    }
}
