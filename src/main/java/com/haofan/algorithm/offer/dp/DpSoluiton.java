package com.haofan.algorithm.offer.trie;


public class DpSolution {
    /**
     * 面试题88：爬楼梯的最少成本。
     *
     * 分析：关键是每次爬的时候，可以往上爬1级台阶也可以爬2级台阶，所有就有了最优解。
     * f(i) = 从第i台阶 往上爬的最少成本，如果一个楼梯有n级台阶，那么最优解就是f(n-1) 和 f(n-2)的最小值，
     * f(i)= min(f(i-2), f(i-1)) + cost(i)
     *
     * 思路1：可以用递归解法，把大问题拆成小问题，然后再合并结果, 递归的问题是很多重复的解。
     */
    public int minCostClimbingStaris(int[] cost) {
        int len = cost.length;
        return Math.min(helper(cost, len - 2), helper(cost, len - 1));
    }

    private int helper(int[] cost, int i) {
        if (i < 2) {
            return cost[i];
        }

        return Math.min(helper(cost[i-2]), helper(cost[i-1])) + cost[i];
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

        return Math.min(dp[len - 2], dp [len - 1]);
    }
}
