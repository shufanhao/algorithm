package com.haofan.algorithm.leetcodecn.easy.search;


public class Solution {
    public static void main(String args[]) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        Solution s = new Solution();
        s.merge(nums1, 3, nums2, 3);
        System.out.println("题目1：");
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i] + ",");
        }

        System.out.println("题目3： " + s.binarySearch(nums2, 5));
    }

    /**
     * 题目1：合并两个有序数组，注意是有序数组
     * 思路：两个指针分别指向两个数组，记录位置，如果从前向后排序的话，会涉及到数组元素的多次地洞
     * ，所以从后向前排序
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i, j, k;
        // for 条件写的比较好
        for (i = m - 1, j = n - 1, k = m + n - 1; k >= 0; k--) {
            if ((i >= 0) && (j < 0 || nums1[i] > nums2[j])) {
                nums1[k] = nums1[i--];
            } else {
                nums1[k] = nums2[j--];
            }
        }
    }

    /**
     * 题目2：第二个错误的版本
     * 思路：二分法查找，因为这个题目是继承自VersionControl，没有写这个class所以没法跑起来
     */
    public int firstBadVersion(int n) {
        int min = 1, max = n, mid = 0;
        while (min <= max) {
            min = min + (max - min) / 2;
            /*if (isBadVersion(mid)) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }*/
        }
        return mid;
    }

    /**
     * 题目3： 二分查找
     *
     * @param nums 输入的有序数组
     * @param k    输入的要找的元素
     * @return 要找的元素的下标
     */
    public int binarySearch(int nums[], int k) {
        int low = 0;
        int high = nums.length - 1;
        int mid = 0;
        if (k < nums[low] || k > nums[high] || low > high) {
            return -1;
        }
        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] > k) {
                high = mid - 1;
            } else if (nums[mid] < k) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
