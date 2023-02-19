package com.haofan.algorithm.leetcodecn.easy.dynamic;


// 动态规划问题，关键求出公式，找出规律即可
public class DynamicPlanning {
    public static void main(String args[]) {
        DynamicPlanning dynamicPlanning = new DynamicPlanning();
        System.out.println("题目1：" + dynamicPlanning.climbStairs(3));

        int prices[] = {3, 2, 3, 1, 2};
        System.out.println("题目2：" + dynamicPlanning.maxProfit(prices));

        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("题目3：" + dynamicPlanning.maxSubArray(nums));

        int[] money = {2, 7, 9, 3, 1};
        System.out.println("题目4：" + dynamicPlanning.rob(money));
    }

    /**
     * 题目1：假设在爬楼梯，需要阶才能达到楼顶，每次可以爬1或2个台阶，有多少种不同的方法可以爬到楼梯
     * 思路：arr[i] = arr[i-1] + arr[i-2]
     */
    public int climbStairs(int n) {
        int[] array = new int[n + 1];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i < (n + 1); i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }

    /**
     * 买卖股票的最佳时机
     * 动态规划：选取最小的买，最大的卖，这样利润最大
     */
    public int maxProfit(int[] prices) {
        int result = 0; // 利润
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int minBuy = prices[0];
        for (int i = 1; i < prices.length; i++) {
            result = Math.max(result, prices[i] - minBuy);
            minBuy = Math.min(minBuy, prices[i]);
        }
        return result;
    }

    /**
     * 题目3：最大子序列，给定一个整数数组nums, 找到一个具有最大和的连续子数组，
     * https://blog.csdn.net/zwzsdy/article/details/80029796
     * 子数组最少包含一个元素，返回其最大和
     * sum[i]是以第i个元素结尾且和最大的连续子数组：
     * sum[i] = max(sum[i-1] + a[i], a[i], 实际上就是判断sum[i-1]是否大于0
     */
    public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int sum_1 = nums[0]; // sum[i-1]
        for (int i = 1; i < nums.length; i++) {
            if (sum_1 > 0) {
                sum_1 = sum_1 + nums[i];
            } else {
                sum_1 = nums[i];
            }
            if (sum < sum_1) {
                sum = sum_1;
            }
        }
        return sum;
    }

    /**
     * 题目4：打家劫舍问题
     * 就是公式：dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1])
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length + 1];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }
}
