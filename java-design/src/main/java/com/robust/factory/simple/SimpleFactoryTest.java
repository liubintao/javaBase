package com.robust.factory.simple;

/**
 * @author bintao
 * @Despription 客户需要知道具体产品的配置信息才能得到具体的产品
 * @date 2018/8/26 21:13
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        System.out.println(SimpleFactory.getProduct("A").getName());
        System.out.println(SimpleFactory.getProduct("B").getName());
    }
}
