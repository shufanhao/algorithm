package com.haofan.algorithm.offer.dp;


import com.haofan.algorithm.help.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 面试题62：不同路径
     * <a href="https://leetcode.cn/problems/unique-paths/">...</a>
     * 典型的 动态规划，因为：
     * dp[i][j] = dp[i-1][j] + dp[i][j-1]
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // 初始化，第一行或者第一列肯定都是1
        for (int i=0; i < m; i++) dp[i][0] = 1;
        for (int i=0; i < n; i++) dp[0][i] = 1;

        // 遍历二维数组，然后求出dp[i][j] = dp[i-1][j] + dp[i][j-1]
        for (int i=1; i < m; i++) {
            for (int j=1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m -1][n -1];
    }

    /**
     * 面试题64：最小路径和
     * https://leetcode.cn/problems/minimum-path-sum/description/
     * 思路和上面的题目很像，找规律：
     * dp[i][j] = Min(dp[i-1][j] + grid[i][j], dp[i][j-1] + grid[i][j])
     */
    public int minPathSum(int[][] grid) {
        int width = grid[0].length;
        int height = grid.length;
        if (height == 0 || width == 0) return 0;

        // 初始化
        for (int i = 1; i < height; i++) grid[i][0] += grid[i -1][0];
        for (int i = 1; i < width; i++) grid[0][i] += grid[0][i-1];

        for (int i=1; i< height; i++) {
            for (int j=1; j< width; j++) {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }
        return grid[height-1][width-1];
    }

    /**
     * 面试题70: 爬楼梯
     * <a href="https://leetcode.cn/problems/climbing-stairs/description/">...</a>
     * 思路就是：dp[i] = dp[i-1] + dp[i-2]，这个需要通过规律递推出来
     */
    public int climbStairs(int n) {
        if (n == 1) return 1;
        int[] dp = new int[n +1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i=3; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    // dp[i] = dp[i-2] + arr[i], dp[i-1]
    public int rob1(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 0;
        dp[1] = nums[1];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return nums[nums.length - 1];
    }

    /**
     * 面试题221：最大正方形
     * <a href="https://leetcode.cn/problems/maximal-square/description/">...</a>
     *
     * dp[i][j]表示以第i行第j列为右下角所能构成的最大正方形边长, 则递推式为:
     * dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]);
     *
     * 默念一句话: 动态规划每一步填表都建立在之前已经填完的表上.
     * 表的值的意义和你return的值的意义一样（不要多想）！！！
     * 不要考虑初始化边缘（千万不要从dp[0][0]想思路！！！），从中间取一个点，想这个点的值怎么根据它的左边、上边、左上边、左下边的值计算出来！！！
     */
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }

        int row = matrix.length;
        int column = matrix[0].length;

        int[][] dp = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = 1 + Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]);
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }

    /**
     * 面试题322. 零钱兑换
     * https://leetcode.cn/problems/coin-change/description/
     *
     * https://programmercarl.com/0518.零钱兑换II.html#总结
     * 给你一个整数数组 coins，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     *
     * 计算并返回可以凑成总金额所需的 最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     *
     * 例子：amount = 5, coins = [1, 2, 5]
     *
     * 解题思路：
     * 背包问题，实际上是装满 j 的背包，有多少种dp[j] 种方法，最终是dp[amount]，实际上是求的组合数
     *
     * // 如果j - coins[i]是一个有效的金额（即非负），
     * // 那么它表示我们可以在组成金额j的过程中，先使用一枚coins[i]硬币，然后剩下的j - coins[i]金额可以由之前的组合方式组成。
     * 递推公式：dp[j] += dp[j - coins[i]], 初始化：dp[0] = 1, dp[1] = 1
     */
    public int change(int amount, int[] coins) {
        int [] dp = new int[amount + 1];
        dp[0] = 1;
        // 先遍历物品
        for (int i = 0; i < coins.length; i++) {
            // 再遍历背包，求的是组合数
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

    /**
     * 面试题279：完全平方数
     *
     * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
     * 例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     *
     * 完全平方数就是物品(可以无限条件使用)，凑个正整数就是背包，问凑满背包最少多少个物品。
     *
     * dp[j]: 和为j的完全平方数最少得数量为dp[j]
     * 有些难度。
     * dp[j] = min(dp[j - i*i] + 1, dp[j])
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        // 遍历物品
        for (int i = 1; i*i <=n; i++) {
            // 遍历背包
            for (int j = i*i; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }
        return dp[n];
    }

    /**
     * 面试题300：最长递增子序列
     *
     * 有些难以理解。
     * <a href="https://leetcode.cn/problems/longest-increasing-subsequence/description/">...</a>
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     *
     * 思路：
     * 1. 确定dp数组，dp[i] 为考虑前i个元素的最长子序列。
     * 2. dp[i] = Max(dp[j]) + 1, 这里的j其实是从0到i的枚举。
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;

        for (int i = 1; i < nums.length; i++) {
            // 初始化dp[i] = 1
            dp[i] = 1;
            // 枚举从j，从0到i
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    // 因为i和j 可能不连续，所以这里一定是dp[j] + 1
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    /**
     * 打家劫舍 III
     * <a href="https://leetcode.cn/problems/house-robber-iii/">...</a>
     *
     * 思路：一颗二叉树，树上每个节点都有权值，就是选中和不选中，问不能同时选中有父子关系的点的情况下，能选中节点的最大权值的和
     * 是多少？
     * f(o) 表示选择 o 节点的情况下，o 节点的子树上被选择的节点的最大权值和；
     * g(o) 表示不选择 o 节点的情况下，o 节点的子树上被选择的节点的最大权值和.
     *
     * l 和 r 代表 o 的左右孩子
     *
     * 当 o 被选中时，o 的左右孩子都不能被选中，故 o 被选中情况下子树上被选中点的最大权值和为 l 和 r 不被选中的最大权值和相加，即 f(o)=g(l)+g(r)。
     * 当 o 不被选中时，o的左右孩子可以被选中也可以不被选中，所以g(0) = max{f(l), g(l)} + max{f(r),g(r)}
     */
    public int rob3(TreeNode root) {
        // two map to store f,g
        Map<TreeNode, Integer> f = new HashMap<>();
        Map<TreeNode, Integer> g = new HashMap<>();

        // 一定是后序遍历，因为后续遍历才能找到最后的累加的和
        dfs(root, f, g);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    private void dfs(TreeNode node, Map<TreeNode, Integer> f,  Map<TreeNode, Integer> g) {
        if (node == null) {
            return;
        }

        dfs(node.left, f, g);
        dfs(node.right, f, g);

        // 当 o 被选中时，o 的左右孩子都不能被选中，故 o 被选中情况下子树上被选中点的最大权值和为 l 和 r 不被选中的最大权值和相加。
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));

        // o 不被选中时，o的左右孩子可以被选中也可以不被选中，所以g(0) = max{f(l), g(l)} + max{f(r),g(r)}
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }
}
