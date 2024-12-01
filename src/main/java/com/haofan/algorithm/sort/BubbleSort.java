package com.haofan.algorithm.sort;

import static com.haofan.algorithm.help.Common.swap;

//https://www.cnblogs.com/shen-hua/p/5422676.html

public class BubbleSort {
    /**
     * 首先比较第1个和第2个数，将小数放前，大数放后。
     * 然后比较第2个数和第3个数，将小数放前，大数放后，如此继续，直至比较最后两个数，将小数放前，大数放后。
     * 最后是大数在最后面，所以内层循环应该是num.length - 1 - j
     * 重复第一趟步骤，直至全部排序完成。
     */
    public static int[] sort(int[] num) {
        // 外层循环控制排序趟数
        for (int j = 0; j < num.length - 1; j++) {
            // 内层循环控制每一趟排序多少次
            for (int i = 0; i < num.length - 1 - j; i++) {
                if (num[i] > num[i + 1]) {
                    swap(num, i + 1, i);
                }
            }
        }
        return num;
    }
}

