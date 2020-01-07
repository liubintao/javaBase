package com.robust.basis.bitwise;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @Description: 位操作常用小技巧
 * @Author: robust
 * @CreateDate: 2019/12/4 10:34
 * @Version: 1.0
 */
public class Tips {

    /**
     * 只要根据最未位是0还是1来决定，为0就是偶数，为1就是奇数。因此可以用if ((a & 1) == 0)代替if (a % 2 == 0)来判断a是不是偶数。
     * 下面程序将输出0到100之间的所有奇数。
     */
    static void oddOrEven() {
        IntStream.rangeClosed(1, 100)
                .filter((i) -> (i & 1) == 1)
                .forEach(System.out::println);
    }

    /**
     * 交换两数
     * 第一步：a = a^b
     * 第二部：b = a^b = （a^b）^b=第一步的值b。由于运算满足交换律，(a^b)^b=a^b^b。由于一个数和自己异或的结果为0并且任何数与0异或都会不变的，所以此时b被赋上了a的值。
     * 第三步 a=a^b，由于前面二步可知a=(a^b)，b=a，所以a=a^b即a=(a^b)^a。故a会被赋上b的值。
     *
     * @param a
     * @param b
     */
    static void swap(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a=" + a + ",b= " + b);
    }

    /**
     * 变换符号(反转符号)
     * 变换符号就是正数变成负数，负数变成正数。
     * 如对于-11和11，可以通过下面的变换方法将-11变成11
     * 1111 0101(二进制) –取反-> 0000 1010(二进制) –加1-> 0000 1011(二进制)
     * 同样可以这样的将11变成-11
     * 0000 1011(二进制) –取反-> 0000 0100(二进制) –加1-> 1111 0101(二进制)
     * 因此变换符号只需要取反后加1即可。
     *
     * @param a
     */
    static void signReversal(int a) {
        a = ~a + 1;
        System.out.println(a);
    }

    /**
     * 位操作也可以用来求绝对值，对于负数可以通过对其取反后加1来得到正数。对-6可以这样：
     * 1111 1010(二进制) –取反->0000 0101(二进制) -加1-> 0000 0110(二进制)
     * 来得到6。
     * 因此先移位来取符号位，int i = a >> 31;要注意如果a为正数，i等于0，为负数，i等于-1。然后对i进行判断——如果i等于0，直接返回。否之，返回~a+1。
     *
     * @param a
     */
    static void abs(int a) {
        a = (a >> 31) == 0 ? a : ~a + 1;
        System.out.println(a);
    }

    /**
     * 对于任何数，与0异或都会保持不变，与-1即0xFFFFFFFF异或就相当于取反。因此，a与i异或后再减i（因为i为0或-1，所以减i即是要么加0要么加1）也可以得到绝对值。
     *
     * @param a
     */
    static void absNoDecide(int a) {
        int i = a >> 31;
        a = (a ^ i) - i;
        System.out.println(a);
    }

    static void getPrime() {
        int max = 100;
        boolean[] flag = new boolean[100];
        int[] primes = new int[max / 3 + 1];

        int i, j;
        int pi = 0;
        for (i = 2; i < max; i++) {
            if (!flag[i]) {
                primes[pi++] = i;
                /**
                 * 对于每个素数，它的倍数必定不是素数
                 */
                for (j = i; j < max; j += i)
                    flag[j] = true;
            }
        }

        Arrays.stream(primes)
                .limit(pi)
                .forEach((p) -> System.out.print(p + ","));
    }

    static void getPrimeByBitwise() {
        int max = 100;
        int[] flag = new int[max/32 + 1];
        int[] primes = new int[max / 3 + 1];

        int i, j;
        int pi = 0;
        for (i = 2; i < max; i++) {
            if ((((flag[i/32] >> (i % 32))& 1) == 0)) {
                primes[pi++] = i;
                /**
                 * 对于每个素数，它的倍数必定不是素数
                 */
                for (j = i; j < max; j += i)
                    flag[j/32] |= (1 << (j % 32));
            }
        }

        Arrays.stream(primes)
                .limit(pi)
                .forEach((p) -> System.out.print(p + ","));
    }

    static void a() {
        int i = 0;
        i |= i << 10;
        System.out.println(i);
    }

    static void b() {
        int[] bits = new int[40];

        for (int m = 0; m < 40; m += 3) {
            bits[m / 32] |= (1 << (m % 32));
        }

        // 输出整个bits
        for (int m = 0; m < 40; m++) {
            if (((bits[m / 32] >> (m % 32)) & 1) != 0) {
                System.out.print('1');
            } else {
                System.out.print('0');
            }
        }
    }

    public static void main(String[] args) {
//        oddOrEven();
//        swap(13, 6);
//        signReversal(-7);
//        abs(6);
//        abs(-9);
//
//        absNoDecide(8);
//        absNoDecide(-10);

//        getPrime();
//        a();
//        b();
        getPrimeByBitwise();
    }
}
