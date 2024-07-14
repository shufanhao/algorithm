package com.haofan.algorithm.offer.dp;


import java.util.Arrays;

public class DpSolution {
    /**
     * 面试题88：爬楼梯的最少成本。
     *
     * <a href="https://leetcode.cn/problems/GzCJIP/description/">...</a>
     * <p>
     * 分析：关键是每次爬的时候，可以往上爬1级台阶也可以爬2级台阶，所有就有了最优解。
     * f(i) = 从第i台阶 往上爬的最少成本，如果一个楼梯有n级台阶，那么最优解就是f(n-1) 和 f(n-2)的最小值，
     * f(i)= min(f(i-2), f(i-1)) + cost(i)
     * <p>
     * 思路1：可以用递归解法，把大问题拆成小问题，然后再合并结果, 递归的问题是很多重复的解。
     *
     * 这里有很多的重复计算
     */
    public int minCostClimbingStaris(int[] cost) {
        int len = cost.length;
        return Math.min(helper(cost, len - 2), helper(cost, len - 1));
    }

    private int helper(int[] cost, int i) {
        if (i < 2) {
            return cost[i];
        }

        return Math.min(helper(cost, i - 2), helper(cost, i - 1)) + cost[i];
    }

    /**
     * 上面问题的思路2：可以从小问题开始。dp数组用于记录每一步的缓存结果
     * 时间复杂度：O(n)
     * 空间: O(n)
     */
    public int minCostClimbingStaris2(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i=2; i< cost.length; i++) {
            dp[i] = Math.min(dp[i - 2], dp[i - 1]) + cost[i];
        }

        return Math.min(dp[len - 2], dp[len - 1]);
    }

    /***
     * 面试题89： 房屋偷盗
     * <a href="https://leetcode.cn/problems/Gu0c2T/submissions/545062854/">...</a>
     * 分析，关键要找出状态转移方程，f(i) 表示小偷从标号为0的房屋开始到标号为i的房屋最多能偷到的财务。
     * 最后f(i) = max(f(i - 2) + nums[i], f(i - 1))
     */
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        if (nums.length > 1) {
            dp[1] = Math.max(nums[0], nums[1]);
        }
        for (int i = 2; i< nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    /**
     * 面试题89：环形房屋
     *
     * 分析：和上面的题目类似，区别在于小偷不能连续进入 第0个房屋和第n-1个房屋。那么问题可以分解成
     * 取：0 -> n -2 ，取出最大值
     *    1 -> n -1, 取出最大值
     * 上面两个最大值，再取最大值即可
     */
    public int robCycle(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int res1, res2 = 0;
        int[] arr1 = new int[nums.length - 1];
        int[] arr2 = new int[nums.length - 1];
        for (int i =0; i < nums.length; i++) {
            if (i <= nums.length - 2) {
                arr1[i] = nums[i];
            }

            if (i >= 1) {
                arr2[i-1] = nums[i];
            }
        }

        return Math.max(rob(arr1), rob(arr2));
    }
}
