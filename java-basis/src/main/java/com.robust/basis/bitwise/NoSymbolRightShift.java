package com.robust.basis.bitwise;

/**
 * Created by bintao on 2017/8/11.
 * 低位溢出，高位补0。注意，无符号右移（>>>）中的符号位（最高位）也跟着变，无符号的意思是将符号位当作数字位看待
 */
public class NoSymbolRightShift {

    private static void test(){
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-1 >>> 1));
        System.out.println(Integer.toBinaryString(-2));

    }

    public static void main(String[] args) {
        test();
    }
}
