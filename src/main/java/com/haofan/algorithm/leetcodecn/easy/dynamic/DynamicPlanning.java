package com.haofan.algorithm.leetcodecn.easy.dynamic;


// 动态规划问题，关键求出公式，找出规律即可
public class DynamicPlanning {
    public static void main(String args[]) {
        DynamicPlanning dynamicPlanning = new DynamicPlanning();

        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("题目3：" + dynamicPlanning.maxSubArray(nums));
    }

    /**
     * 题目3：最大子序列，给定一个整数数组nums, 找到一个具有最大和的连续子数组，
     * https://leetcode.cn/problems/maximum-subarray/solutions/228009/zui-da-zi-xu-he-by-leetcode-solution
     * 子数组最少包含一个元素，返回其最大和
     */
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        int tempTotal = 0;
        for (int i=0; i<nums.length; i++) {
            tempTotal += nums[i];
            result = Math.max(tempTotal, result);
            if (tempTotal < 0) {
                tempTotal = 0;
            }
        }
        return result;
    }
}
