package com.robust.factory.function;

/**
 * @author bintao
 * @Despription 客户需要知道具体的产品工厂
 * @date 2018/8/26 21:18
 */
public class FunctionFactoryTest {
    public static void main(String[] args) {
        Factory factory = new ProductAFactory();
        System.out.println(factory.getProduct().getName());
        factory = new ProductBFactory();
        System.out.println(factory.getProduct().getName());
    }
}
