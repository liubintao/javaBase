package com.itliu.algorithm.sort;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by neil on 2017/4/28.
 * 冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果他们的顺序错误就把他们交换过来。
 * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会
 * 经由交换慢慢“浮”到数列的顶端。
 */
public class BobbleSorting {

    /**
     * 冒泡排序
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     * @param numbers 需要排序的整型数组
     */
    public static void bubbleSort(int[] numbers)
    {
        int temp = 0;
        int size = numbers.length;
        for(int i = 0 ; i < size-1; i ++)
        {
            for(int j = 0 ;j < size-1-i ; j++)
            {
                if(numbers[j] > numbers[j+1])  //交换两数位置
                {
                    temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        List list = Lists.newArrayList(24,12,35,20,18,92);
        System.out.println(list);

        int[] intArray = new int[]{24,12,35,20,18,92};
        /*System.out.println(Arrays.toString(intArray));
        bubbleSort(intArray);
        System.out.println(Arrays.toString(intArray));*/

    }
}
