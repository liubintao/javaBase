package com.robust.shift;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by neil on 2017/5/4.
 */
public class ShiftUse {

    /**
     * m*2^n
     */
    private static void leftShift(){
        System.out.println("2^3=" + (1 << 3));//2^3=8
        System.out.println("3*2^3=" + (3 << 3));//3*2^3=24

        //minInt
        System.out.println(1 << 31);
        System.out.println(1 << -1);
        /**
         * 可以发现左移31位和-1位所得的结果是一样的，同理，左移30位和左移-2所得的结果也是一样的。移动一个负数位，是不是等同于右移该负数的绝对值位呢？输出一下就能发现不是的。java中int所能表示的最大数值是31位，加上符号位共32位。在这里可以有这样的位移法则：
         法则一：任何数左移（右移）32的倍数位等于该数本身。
         法则二：在位移运算m<<n的计算中，若n为正数，则实际移动的位数为n%32，若n为负数，则实际移动的位数为(32+n%32)，右移，同理。
         左移是乘以2的幂，对应着右移则是除以2的幂。
         */
    }

    /**
     * n&1 == 1?”奇数”:”偶数”
     为什么与1能判断奇偶？所谓的二进制就是满2进1，那么好了，偶数的最低位肯定是0（恰好满2，对不对？），同理，奇数的最低位肯定是1.int类型的1，前31位都是0，无论是1&0还是0&0结果都是0，那么有区别的就是1的最低位上的1了，若n的二进制最低位是1（奇数）与上1，结果为1，反则结果为0.
     */
    private static void and(){
        System.out.println(3 & 1);
        System.out.println(4 & 1);
    }

    private static void xor(){
        int[] array = new int[]{1,2,3,4,5};
        System.out.println(Arrays.toString(reverse(array)));
    }

    /**
     * 连续三次使用异或，并没有临时变量就完成了两个数字交换，怎么实现的呢？

     下面的计算主要遵循了一个计算公式：b^(a^b)=a。
     我们可以对以上公式做如下的推导：
     任何数异或本身结果为0.且有定理a^b=b^a。异或是一个无顺序的运算符，则b^a^b=b^b^a，结果为0^a。
     再次列出异或的计算表:
     操作数1    0 0 1 1
     操作数2    0 1 0 1
     按位异或   0 1 1 0
     可以发现，异或0具有保持的特点，而异或1具有翻转的特点。使用这些特点可以进行取数的操作。
     那么0^a，使用异或0具有保持的特点，最终结果就是a。
     其实java中的异或运算法则完全遵守数学中的计算法则：
     ①    a ^ a =0
     ②    a ^ b =b ^ a
     ③    a ^b ^ c = a ^ (b ^ c) = (a ^ b) ^ c;
     ④    d = a ^b ^ c 可以推出 a = d ^ b ^ c.
     ⑤    a ^ b ^a = b.
     */
    public static int[] reverse(int[] nums){
        int i = 0;
        int j = nums.length-1;
        while(j>i){
            nums[i]= nums[i]^nums[j];
            nums[j] = nums[j]^nums[i];
            nums[i] = nums[i]^nums[j];
            j--;
            i++;
        }
        return nums;
    }

    private static void delivery(){
        System.out.println(16 % 2);
        System.out.println(16 & 1);
    }

    public static void main(String[] args) {
        leftShift();
        and();
        xor();
        delivery();
        /*System.out.println(1 << 4);
        System.out.println(1 << 30);

        System.out.println(Objects.hashCode("aaa"));
        System.out.println(Objects.hashCode("bbb"));

        int h;
        Object object = "abc";
        System.out.println((h = object.hashCode()) ^ h >>> 16);*/
    }
}
