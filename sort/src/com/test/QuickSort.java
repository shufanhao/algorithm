package com.test;

public class QuickSort {

    //http://wiki.jikexueyuan.com/project/easy-learn-algorithm/fast-sort.html
    void quickSort(int arr[], int left, int right) {
        if (left >= right) {
            return;
        }
        // 选取数组中第一个元素，作为哨兵
        // 把比哨兵小的数，放在哨兵的左侧，比哨兵大的数，放在右边
        int pivot = arr[left];
        int i,j,temp; //i，j 指向序列最左边和最右边
        i = left;
        j = right;
        while (i != j) {
            //从右边开始找，顺序非常重要
            while (arr[j] >= pivot && i<j) {
                j--; //继续找
            }
            while (arr[i] <= pivot && i<j) {
                i++;// 继续找
            }
            // 找到了，交换两个元素位置
            if (i<j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        //将哨兵归为
        arr[left] = arr[i];
        arr[i] = pivot;
        quickSort(arr, left, i -1); //继续处理左边的
        quickSort(arr, i+1, right); // 继续处理右边的
    }
    public static void main(String args[]) {
        QuickSort obj = new QuickSort();
        int[] nums = {3,4,1,5,6};
        obj.quickSort(nums, 0, nums.length -1);
        for ( int n : nums) {
            System.out.println(n);
        }
    }
}