package com.haofan.algorithm.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String args[]) {
        QuickSort obj = new QuickSort();
        int[] nums = {3, 4, 1, 5, 6};
        obj.quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 算法平均时间复杂度：N*logN, 最糟糕：N^2（正序或逆序）
     * 大致思路还是排序算法中比较简单的：
     */

    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // pi是partition的索引，arr[pi]现在位于正确的位置
            int pi = partition(arr, low, high);

            // 分别对基准值前后的子数组进行排序
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // 这个函数用于分区操作，返回基准值的最终位置
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[low]; // 选择第一个元素作为基准值
        while (low < high) {
            // 从后向前找第一个小于等于pivot的元素
            while (low < high && arr[high] > pivot) {
                high--;
            }
            // 将这个元素放到它应该在的位置
            arr[low] = arr[high];

            // 从前向后找第一个大于pivot的元素
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            // 将这个元素放到它应该在的位置
            arr[high] = arr[low];
        }

        // 将基准值放到中间
        arr[low] = pivot;
        return low;
    }
}
