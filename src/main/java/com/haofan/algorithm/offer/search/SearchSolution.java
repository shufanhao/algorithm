package com.haofan.algorithm.offer.search;

public class SearchSolution {
    /**
     * 在排序数组中二分查找
     * 1, 2, 3, 4, 5, 6, target 2
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 面试题68：给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
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
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                // nums[mid] > target
                if (mid == 0 || nums[mid - 1] < target) {
                    return mid;
                }
                right = mid - 1;
            }
        }
        return nums.length;
    }

    /**
     * 面试题69：山峰数组的顶部
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

    /**
     * 面试题70：排序数组中只出现一次的数组 <a href="https://leetcode.cn/problems/single-element-in-a-sorted-array/description/">...</a>
     * <p>
     * 排序数组中，除了一个数字外，其他数字都出现两次。例如[1,1,2,2,3,4,4,5,5]，只有3出现了一次。
     * 要求找到这个数字。
     * <p>
     * 解法：如果不是排序数组的话，那么可以通过异或，最后的结果就是只出现了一次的数字。但是时间复杂度是O(n)，因为是排序数组，则可以考虑O(logn)的解法
     * 通过两两分组，{1, 1}，{2, 2}, {3, 4}, {4, 5}, {5} 可以发现，找到第一个分组中不相同的数字，就是要求找到的只出现一次的数字。
     *
     * 所有可以用二分查找: 分成6组，
     */
    public int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            // 如果mid 是偶数则比较
            if (nums[mid] == nums[mid ^ 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }
}
