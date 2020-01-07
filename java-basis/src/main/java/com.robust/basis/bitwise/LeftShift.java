package com.robust.basis.bitwise;

/**
 * Created by bintao on 2017/8/11.
 * 位移之左移 <<
 * 左移运算符，将运算符左边的对象向左移动运算符右边指定的位数（在低位补0）
 * 00000000000000000000000000100101
 * 左移2位则为
 * 00000000000000000000000010010100
 */
public class LeftShift {

    static {
        /**
         * 此处顺带验证一个问题：调用class的static方法会先初始化class
         */
        System.out.println("this class init");
    }

    /**
     * << 左移符号测试
     */
    private static void leftShiftMethod(){
        /**
         * 2*2^2=8
         */
        System.out.println(2 << 2);
        /**
         * 3*2^4=48
         */
        System.out.println(3 << 4);
    }

    /**
     * 理解二进制转10进制测试
     */
    private static void str2Decimalism(){
        /**
         * 普通32位二进制数,假设索引从右侧开始，最左边的1索引(偏移量)为5，第二个1索引为2，最右侧1索引为0，
         * 则：1*2^5 + 1*2^2 + 1*2^0 = 37;
         */
        String s = "00000000000000000000000000100101";
        System.out.println(Integer.valueOf(s, 2));
    }

    public static void main(String[] args) {
        leftShiftMethod();
        str2Decimalism();

    }
}
