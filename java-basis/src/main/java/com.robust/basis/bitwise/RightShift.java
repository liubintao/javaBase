package com.robust.basis.bitwise;

/**
 * Created by bintao on 2017/8/11.
 * 位移之右移 >>
 *"有符号"右移运算 符，将运算符左边的对象向右移动运算符右边指定的位数。使用符号扩展机制，也就是说，如果值为正，则在高位补0，如果值为负，则在高位补1.
 * 00000000000000000000000000100101
 * 右移2位则为
 * 00000000000000000000000000001001
 *
 *
 *  计算机对有符号数（包括浮点数）的表示有三种方法：原码、反码和补码， 补码=反码+1。在 二进制里，是用 0 和 1 来表示正负的，最高位为符号位，最高位为 1 代表负数，最高位为 0 代表正数。
 以Java中8位的byte为例，最大值为：0111 1111，最小值为1000 0001。
 那么根据十进制的数字，我们如何转换为二进制呢？对于正数我们直接转换即可，对于负数则有一个过程。
 以负数-5为例：
 1.先将-5的绝对值转换成二进制，即为0000 0101；
 2.然后求该二进制的反码，即为 1111 1010；
 3.最后将反码加1，即为：1111 1011
 所以Java中Integer.toBinaryString(-5)结果为11111111111111111111111111111011. Integer是32位(bit)的.
 */
public class RightShift {

    private static void RightShiftMethod(){

        System.out.println(5 >> 2);
        System.out.println(-5 >> 2);

        System.out.println(8 >> 2);
        System.out.println(-8 >> 2);

        System.out.println(2039 >> 5);
        System.out.println(-2039 >> 5);

    }

    public static void main(String[] args) {
        RightShiftMethod();
    }
}
