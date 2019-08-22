package com.robust.basis.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/3/11 17:20
 * @Version: 1.0
 */
public class SimilariryRatio {

    /**
     * 比较两个字符串的相识度
     * 核心算法：用一个二维数组记录每个字符串是否相同，如果相同记为0，不相同记为1，每行每列相同个数累加
     * 则数组最后一个数为不相同的总数，从而判断这两个字符的相识度
     *
     * @param str
     * @param target
     * @return
     */
    private static int compare(String str, String target) {
        int d[][];              // 矩阵
        int n = str.length();
        int m = target.length();
        int i;                  // 遍历str的
        int j;                  // 遍历target的
        char ch1;               // str的
        char ch2;               // target的
        int temp;               // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        // 初始化第一列
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        // 初始化第一行
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }
        for (i = 1; i <= n; i++) {
            // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }


    /**
     * 获取最小的值
     */
    private static int min(int one, int two, int three) {
        return (one = one < two ? one : two) < three ? one : three;
    }

    /**
     * 获取两字符串的相似度
     */
    public static float getSimilarityRatio(String str, String target) {
        int max = Math.max(str.length(), target.length());
        return 1 - (float) compare(str, target) / max;
    }

    public static void main(String[] args) throws IOException {
        /*String a= "UPDATE us_info SET CSI_NEWFLAG='1'  WHERE  (CSI_CERTNO = 'zhangnan'  or  CSI_USERNAME='zhangnan' or CSI_MOBILE = 'zhangnan' );";
        String b = "UPDATE us_info SET CSI_NEWFLAG='1'  WHERE  (CSI_CERTNO = 'xtyhwsm'  or  CSI_USERNAME='xtyhwsm' or CSI_MOBILE = 'xtyhwsm' );";
        System.out.println("相似度："+getSimilarityRatio(a,b));*/

        /*Path path = Paths.get("I:\\wangyinlog\\elog\\slow5.log");
        Path path1 = Paths.get("I:\\wangyinlog\\elog\\slow6.log");
        List<String> list = Files.readAllLines(path);
        LinkedList<String> linkedList = new LinkedList<>(list);
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>(list);
        ConcurrentSkipListMap<String,Object> concurrentSkipListMap = new ConcurrentSkipListMap<>();
        final Object object = new Object();

        for (int i = 0, j = list.size(); i < j; i++) {
            concurrentSkipListMap.put(list.get(i), object);
        }

        List<String> list1 = new ArrayList<>();
        for (int i = 0, j = list.size(); i < j; i++) {
            String str = list.get(i);
            Iterator<String> iterator = concurrentSkipListMap.keySet().iterator();
            while (iterator.hasNext()) {
                String str1 = iterator.next();
                if(!str.equals(str1) && getSimilarityRatio(str, str1) > 0.80) {
                    concurrentSkipListMap.remove(str1);
                }
            }
            for (int h = 0; h < copyOnWriteArrayList.size(); h++) {
                if(h == 42270) {
                    System.out.println(1);
                }
                String str1 = copyOnWriteArrayList.get(h);

                if(!str.equals(str1) && getSimilarityRatio(str, str1) > 0.93) {
                    copyOnWriteArrayList.remove(str1);
                }
            }
        }
        Files.write(path1, concurrentSkipListMap.keySet());*/
        String aa = "UPDATE us_info SET CSI_NEWFLAG='1'  WHERE  (CSI_CERTNO = '130521199212214027'  or  CSI_USERNAME='130521199212214027' or CSI_MOBILE = '130521199212214027' );";
        Path path = Paths.get("I:\\wangyinlog\\elog\\slow.log");
        List<String> list = Files.readAllLines(path);
        for (int i = 0, j = list.size(); i < j; i++) {
            String str = list.get(i);
            if (!str.equals(aa) && getSimilarityRatio(str, aa) > 0.80) {
                System.out.println(str);
            }
        }
    }


}
