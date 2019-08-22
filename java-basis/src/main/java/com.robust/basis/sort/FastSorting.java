package com.robust.basis.sort;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * Created by neil on 2017/4/28.
 * 通过一趟排序将待排序记录分割成独立的两部分，其中一部分记录的关键字均比另一部分关键字小，则分别对这两部分继续进行排
 * 序，直到整个序列有序。
 * 把整个序列看做一个数组，把第零个位置看做中轴，和最后一个比，如果比它小交换，比它大不做任何处理；交换了以后再和小的
 * 那端比，比它小不交换，比他大交换。这样循环往复，一趟排序完成，左边就是比中轴小的，右边就是比中轴大的，然后再用分治
 * 法，分别对这两个独立的数组进行排序。
 */
public class FastSorting {

    /**
     * 快速排序 step1
     * 查找出中轴（默认是最低位low）的在numbers数组排序后所在位置
     *
     * @param numbers 待查找数组
     * @param low   开始位置
     * @param high  结束位置
     * @return  中轴所在位置
     */
    public static int getMiddle(int[] numbers, int low,int high)
    {
        int temp = numbers[low]; //数组的第一个作为中轴
        while(low < high)
        {
            while(low < high && numbers[high] > temp)
            {
                high--;
            }
            numbers[low] = numbers[high];//比中轴小的记录移到低端
            while(low < high && numbers[low] < temp)
            {
                low++;
            }
            numbers[high] = numbers[low] ; //比中轴大的记录移到高端
        }
        numbers[low] = temp ; //中轴记录到尾
        return low ; // 返回中轴的位置
    }

    /**
     *快速排序 step2
     * 分治算法
     * @param numbers 带排序数组
     * @param low  开始位置
     * @param high 结束位置
     */
    public static void quickSort(int[] numbers,int low,int high)
    {
        if(low < high)
        {
        int middle = getMiddle(numbers,low,high); //将numbers数组进行一分为二
        quickSort(numbers, low, middle-1);   //对低字段表进行递归排序
        quickSort(numbers, middle+1, high); //对高字段表进行递归排序
        }

    }

    /**
     * 快速排序 step3 提供方法调用
     * 快速排序
     * @param numbers 带排序数组
     */
    public static void quick(int[] numbers)
    {
        if(numbers.length > 0)   //查看数组是否为空
        {
            quickSort(numbers, 0, numbers.length-1);
        }
    }

    public static void main(String[] args) {
        List list = Lists.newArrayList(24,12,35,20,18,92);
        System.out.println(list);

        int[] intArray = new int[]{24,12,35,20,18,92};

//        getMiddle(intArray,0,intArray.length - 1);
        quick(intArray);
        System.out.println(Arrays.toString(intArray));
    }
}
