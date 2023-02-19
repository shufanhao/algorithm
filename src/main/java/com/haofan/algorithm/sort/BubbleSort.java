package com.haofan.algorithm.sort;

import java.util.Arrays;

//https://www.cnblogs.com/shen-hua/p/5422676.html

public class BubbleSort {
    /**
     * 首先比较第1个和第2个数，将小数放前，大数放后。
     * 然后比较第2个数和第3个数，将小数放前，大数放后，如此继续，直至比较最后两个数，将小数放前，大数放后。
     * 重复第一趟步骤，直至全部排序完成。
     */
    public static void sort(int num[]) {
        if (num == null && num.length == 0) {
            return;
        }
        // 外层循环控制排序趟数
        for (int j = 0; j < num.length - 1; j++) {
            // 内层循环控制每一趟排序多少次
            for (int i = 0; i < num.length - 1 - j; i++) {
                if (num[i] > num[i + 1]) {
                    swap(num, i + 1, i);
                }
            }
        }
    }

    public static void swap(int arr[], int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String args[]) {
        int[] nums = {3, 4, 1, 5, 6};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}

