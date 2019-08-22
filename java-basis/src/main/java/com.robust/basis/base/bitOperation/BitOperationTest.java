package com.robust.basis.base.bitOperation;


import com.robust.basis.utils.StringUtil;
import org.apache.commons.text.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.util.stream.Stream;

/**
 * Created by bintao on 2017/8/11.
 * java位运算测试
 */
public class BitOperationTest {


    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(Math.pow(2,16));
        System.out.println(1 << 16);
        System.out.println(ifAnEvenNumber(2));
        System.out.println(signReversal(2));
        System.out.println(signReversal(-2));
        System.out.println(abs(3));
        System.out.println(abs(-3));
        System.out.println(abs1(-4));
        System.out.println(abs1(4));
        System.out.println(3 >> 31);
        System.out.println(-3 >> 31);
        System.out.println((2 << 1) ^ 0);
        System.out.println(-2 >> 31);
        System.out.println((-2 << 1) ^ (-2 >> 31));
//        System.out.println(Integer.toBinaryString(2));
//        System.out.println(str2HexStr("\r\n"));
//        System.out.println(hex2Str("FEDCBA"));
//        System.out.println(StringEscapeUtils.escapeJava(hex2Str("FF")));
//        System.out.println(StringEscapeUtils.escapeJava(hex2StrBySeparator("4500 0042 08d1 4000 3606 149d 0ab3 b561","")));
    }

    private static String unicodeToCn(String unicode) {
        /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
        if(!unicode.contains("\\\\u")) {
            return unicode;
        }
        String[] strs = unicode.split("\\\\u");
        String returnStr = "";
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for (int i = 1; i < strs.length; i++) {
            returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
        }
        return returnStr;
    }

    /**
     * 字符串转换成为16进制(无需Unicode编码)
     * @param str
     * @return
     */
    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            // sb.append(' ');
        }
        return sb.toString().trim();
    }

    public static String hex2StrBySeparator(String hexStr, String separator) {
        if(StringUtil.isBlank(separator)) {
            separator = " ";
        }
        String[] ss = hexStr.split(separator);
        StringBuilder sb = new StringBuilder();
        Stream.of(ss).forEach(s -> {
            try {
                sb.append(hex2Str(s));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        return sb.toString();
    }

    /**
     * 16进制转字符串(无需Unicode解码)
     * @param hexStr
     * @return
     */
    public static String hex2Str(String hexStr) throws UnsupportedEncodingException {
        /**
         * 16进制字符
         */
        String str = "0123456789ABCDEF";
        /**
         * 16进制数转大写
         */
        hexStr = hexStr.toUpperCase();
        /**
         * 将16进制字符串转char数组
         */
        char[] hexArray = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexArray[2 * i]) * 16;
            n += str.indexOf(hexArray[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /**
     * 判断一个数是否是偶数
     * @param i
     * @return
     */
    public static boolean ifAnEvenNumber(int i) {
        return (i & 1) == 0;
    }

    /**
     * 变换符号
     * @param a
     * @return
     */
    public static int signReversal(int a)
    {
        return ~a + 1;
    }

    /**
     * 求绝对值
     * @param a
     * @return
     */
    static int abs(int a)
    {
        int i = a >> 31;
        return i == 0 ? a : (~a + 1);
    }

    static int abs1(int a)
    {
        int i = a >> 31;
        return (a^i) - i;
    }
}
