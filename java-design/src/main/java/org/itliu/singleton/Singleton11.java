package org.itliu.singleton;

/**
 * @auther itliu
 * @despription 枚举式创建单例
 * 不仅可以解决线程同步，还可以防止反序列化。
 * @data 2020/2/8
 */
public enum Singleton11 {
    INSTANCE;

    public void m() {
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Singleton11.INSTANCE.hashCode());
            }).start();
        }
    }
}
