package com.haofan.algorithm.sort;

public class QuickSort {
    /**
     * 算法平均时间复杂度：N*logN, 最糟糕：N^2（正序或逆序）
     * 大致思路还是排序算法中比较简单的：
     * 对于一个数组，我们选择一个“基准”（pivot）元素，然后将数组重新排列，使得所有比基准小的元素都在基准的左边，
     * 所有比基准大的元素都在基准的右边。这样，基准元素就处于了它在排序后数组中的正确位置。
     * 接下来，我们递归地对基准左边和右边的子数组进行同样的操作。
     * 看这个解释：<a href="https://blog.csdn.net/flin666/article/details/129478364">...</a>
     *
     */
    public void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        int temp = arr[low];
        int i = low;
        int j = high;

        while (i < j) {
            // 从后往前找，找到第一个小于temp 基准元素的值
            while (arr[j] >= temp && j > i) {
                j--;
            }
            // 从前往后找，找到第一个大于temp的基准元素的值
            while (arr[i] <= temp && j> i) {
                i++;
            }
            // 交换元素，后是3, 1, 4, 5 ,6
            if (j > i) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }

        // 继续下一轮循环
        arr[low] = arr[i];
        arr[i] = temp;

        // 根据i的位置分开。
        quickSort(arr, low, i - 1);
        quickSort(arr, i + 1, high);
    }
}
