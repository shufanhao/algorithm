package com.haofan.algorithm.offer.search;

public class SearchSolution {
    /**
     * 面试题1：给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 请必须使用时间复杂度为 O(log n) 的算法。
     * <p>
     * 输入: nums = [1,3,5,6], target = 5
     * 输出: 2
     * <p>
     * 解法：二分查找，左右指针
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) {
                // 比较前一个元素
                if (mid == 0 || nums[mid - 1] < target) {
                    return mid;
                }
            } else {
                left = mid + 1;
            }
        }
        return nums.length;
    }

    /**
     * 面试题2：山峰数组的顶部
     * 给定由整数组成的山峰数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i，即山峰顶部。
     * <p>
     * arr = [1,3,5,4,2] 输出2
     */
    public int peakIndexInMountainArray(int[] nums) {
        int left = 1;
        int right = nums.length - 2;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) {
                return mid;
            }

            if (nums[mid] > nums[mid - 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
