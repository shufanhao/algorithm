package com.test;

import java.util.Arrays;

//https://www.cnblogs.com/chengxiao/p/6194356.html
public class MergeSort {

    public static void main(String args[]) {
        int [] arr = {9,8,7,6,5,4,3,2,1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int arr[]) {
        int[] temp = new int[arr.length];
        sort(arr, 0, arr.length -1, temp);
    }

    public static void sort(int arr[], int left, int right, int temp[]) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(arr, left, mid, temp); //左边归并排序，使得左子序列有序
            sort(arr, mid + 1, right, temp); // 右边归并排序，使得右子序列有序
            merge(arr, left, mid, right, temp); //将两个有序子序列合并操作
        }
    }
    public static void merge(int arr[], int left, int mid, int right, int temp[]) {
        int i = left; //分别用两个指针i,j 指向两个有序数组
        int j = mid + 1;
        int t = 0; //临时数组指针
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        // 将左边剩余元素填充进temp中
        while (i<=mid) {
            temp[t++] = arr[i++];
        }
        // 将右边剩余元素填充进temp中
        while (j<=right) {
            temp[t++] = arr[j++];
        }

        t = 0;
        // 将temp中的元素完全copy 到 原数组中
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }
}
