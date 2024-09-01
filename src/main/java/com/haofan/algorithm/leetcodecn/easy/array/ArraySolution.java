package com.haofan.algorithm.leetcodecn.easy.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.haofan.algorithm.help.Common.swap;

/**
 * 数组问题：
 * 1. 如果要空间复杂度是o(1)的话，应该是肯定有两个指针，start,end,
 * 分别指向数组的第一个元素和最后一个元素，然后分别start ++, end --
 * 2. 考虑先排序
 * 3. 考虑异或，可以知道重复的元素
 * 4. 两个数组交集，考虑两个指针
 */
public class ArraySolution {

    /**
     * 题目1.2：颜色排序
     * http://www.cnblogs.com/grandyang/p/4341243.html
     * 思路：
     * 1. 遍历一遍原数组，分别记录0，1，2的个数， 更新原数组，按个数分别赋上0，1，2。但是需要遍历2遍数组。
     * 2. 如果只要遍历一遍数组，那么定义两个指针，start,end; 从头开始遍历数组，如果end值遇到0，则交换改值和start指向的值，
     * 并将start后移一位。若start值遇到2，则交换改值和end指向的值，并将end前移一位，若遇到1则继续遍历
     */
    public void sortColors(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        for (int i = 0; i <= end; i++) {
            if (nums[i] == 0) {
                swap(nums, i, start++);
            } else if (nums[i] == 2) { //这个地方容易忘记
                swap(nums, i--, end--);
            }
        }
    }

    /**
     * 题目：买卖股票的最佳时机 I
     * <a href="https://blog.csdn.net/qq_45927881/article/details/135729555">...</a>
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *
     * time: O(n), space: O(1)
     */
    public int maxProfitI(int[] prices) {
        int minPrice = prices[0], maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if ((prices[i] - minPrice) > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

    /**
     * 也可以使用动态规划来求解，因为可以分成多个step，每个step又有不同的选择。
     * dp[i] 表示 第i天的最大利润。
     * 对于第i天，如果选择卖出股票，则dp[i] = prices[i] - minPrice, 如果选择不卖出股票，则dp[i] = dp[i-1]
     * 所以，状态转移方程：dp[i] = max(dp[i-1], prices[i] - minPrice)
     */
    public int maxProfitIDP(int[] prices) {
        int[] dp = new int[prices.length];
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            dp[i] = Math.max(dp[i - 1], prices[i] - minPrice);
        }
        return dp[prices.length - 1];
    }
    /**
     * 题目2.1：买卖股票的最佳时机 II(直接求和)
     * 其实就是就一个和：下一天的价格减去前一天的价格差，这个差求和
     **/
    public int maxProfitII(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            // 这个是条件
            if (prices[i] < prices[i + 1]) {
                sum += prices[i + 1] - prices[i];
            }
        }
        return sum;
    }

    /**
     * 题目2.2：买卖股票的最佳时机 III(动态规划), 有些难度，不理解。
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/description/">...</a>
     * 设计一个算法计算获取的最大利润，你最多可以完成 两笔 交易
     * 其实最小买入，最大卖出。
     */
    public int maxProfitIII(int[] prices) {
        /**
         对于任意一天考虑四个变量:
         fstBuy: 在该天第一次买入股票可获得的最大收益
         fstSell: 在该天第一次卖出股票可获得的最大收益
         secBuy: 在该天第二次买入股票可获得的最大收益
         secSell: 在该天第二次卖出股票可获得的最大收益
         分别对四个变量进行相应的更新, 最后secSell就是最大
         收益值(secSell >= fstSell)
         **/
        int fstBuy = Integer.MIN_VALUE, fstSell = 0;
        int secBuy = Integer.MIN_VALUE, secSell = 0;
        for(int p : prices) {
            fstBuy = Math.max(fstBuy, -p);
            fstSell = Math.max(fstSell, fstBuy + p);
            secBuy = Math.max(secBuy, fstSell - p);
            secSell = Math.max(secSell, secBuy + p);
        }
        return secSell;
    }

    /**
     * 题目3：旋转数组/轮转数组 <a href="https://leetcode.cn/problems/rotate-array/description/">...</a>
     * 解法1：利用o(n)的空间复杂度, time O(n)
     * 解法2：空间复杂度是o(1)，先反转前n-k个元素，再反转后k个元素，再整个数组反转
     */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }
        // 解法1：
        /*int [] temp = new int[nums.length];
        k = k % nums.length;
        for (int i=0;i<nums.length;i++) {
            temp[(i+ k)% nums.length] = nums[i];
        }
        for (int i=0; i<nums.length; i++) {
            nums[i] = temp[i];
        }*/

        k = k%nums.length;
        // 解法2：
        reverse(nums, 0, nums.length - 1 - k);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = tmp;
        }
    }

    /**
     * 题目4：存在重复
     * 1. 解法：将数组放到HashMap中，然后检查是否有key，如果有说明，已经存放过。
     * 2. 解法：先将数组排序，然后比较前后2个元素, space O(1), time O(nlogn) 排序算法
     */
    public boolean containsDuplicate(int[] nums) {
        if (nums == null) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 题目6：两个数组的交集 II
     * <a href="https://leetcode.cn/problems/intersection-of-two-arrays-ii/description/">...</a>
     * 先将两个数组排序，然后两个指针分别指向数组1，数组2，根据两个数组的元素大小，决定是否移动指针
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len = Math.min(nums1.length, nums2.length);
        int[] temp = new int[len];
        int i = 0; // i 指向nums1
        int j = 0; // j 指向nums2
        int k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                // 相等的情况：
                temp[k++] = nums1[i];
                i++;
                j++;
            }
        }
        return Arrays.copyOfRange(temp, 0, k);
    }

    /**
     * 题目7： 加一 <a href="https://leetcode.cn/problems/plus-one/">...</a>
     */
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            // 从后向前遍历，[1, 9, 9], 不等于9则直接++, 如果是9的话，则赋值成0
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        // 针对的是[9, 9, 9] 这种case.
        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = 1;
        return newDigits;
    }

    /**
     * 题目8：移动0，类似第一个题目：从排序数组中删除重复的项
     * <a href="https://leetcode.cn/problems/move-zeroes/description/">...</a>
     */
    public int[] moveZeroes(int[] nums) {
        if (nums == null) {
            return null;
        }
        int post = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[post++] = nums[i];
            }
        }
        for (int i = post; i < nums.length; i++) {
            nums[i] = 0;
        }
        return nums;
    }

    /**
     * 题目9： 两数之和 <a href="https://leetcode.cn/problems/two-sum/description/">...</a>
     * 1. 常规思路：o(n的平方) 的时间复杂度，类似于冒泡排序
     * 2. 简单思路：将数组的元素和对应的index，存到Map中
     * 用target 减去 数组的每一个元素，然后将结果在map中检查是否有对应的key
     * 如果有，则返回
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null) {
            return null;
        }
        Map<Integer, Integer> filter = new HashMap<>(nums.length);
        int tmp;
        for (int i = 0; i < nums.length; i++) {
            tmp = target - nums[i];
            if (filter.containsKey(tmp)) {
                return new int[]{i, filter.get(tmp)};
            }
            filter.put(nums[i], i);
        }
        return null;
    }

    /**
     * 题目10：
     * 有效的数独：<a href="https://blog.csdn.net/biezhihua/article/details/79648015">...</a>
     * <a href="https://leetcode.cn/problems/valid-sudoku/">...</a>
     * 1. 抽象出三个二维数组，rawFlag[i][x], 表示在第i行中，出现过数字x, colFlag[j][x] 表示在第j列中出现过数字x
     * cellFlag[][] 表示在第几个方阵中出现过数字x
     * 2. 使用3*(i/3) + j/3 i 代表行的增加，j代表列的增加，可以知道现在处于第几个方阵
     * 3. 如果数字是char 型， 则 int c = x - '1', c 就是对应的数字
     */
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rowFlag = new boolean[9][9];
        boolean[][] colFlag = new boolean[9][9];
        boolean[][] cellFlag = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int num = c - '1';
                    int cell = 3 * (i / 3) + j / 3;
                    if (rowFlag[i][num] || colFlag[j][num] || cellFlag[cell][num]) {
                        return false;
                    }

                    rowFlag[i][num] = true;
                    colFlag[j][num] = true;
                    cellFlag[cell][num] = true;
                }
            }
        }
        return true;
    }

    /**
     * 题目11：
     * 旋转图像：<a href="https://blog.csdn.net/biezhihua/article/details/79653162">...</a>
     *
     * <a href="https://leetcode.cn/problems/rotate-image/description/">...</a>
     * 解法：因为要求不能新的矩阵，所以要看看没有规律，直接调换元素。
     * 先把矩阵的元素按照对角线翻转一下，然后再逐行逆序，就得到顺时针旋转90度后的元素。
     */
    public void rotate(int[][] matrix) {
        int length = matrix.length;
        // 调换对角元素
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[length - j - 1][length - i - 1];
                matrix[length - j - 1][length - i - 1] = tmp;
            }
        }

        // 调换列元素
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length / 2; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[length - j - 1][i];
                matrix[length - j - 1][i] = tmp;
            }
        }
    }

    /**
     * 题目12：输出数组中最大的连续子序列的长度
     * <a href="https://leetcode.cn/problems/longest-consecutive-sequence/">...</a>
     * 要求time O(N)
     * 解法：提供变量去记录之前的最大连续子序列，然后用记录的maxCount去比较即可
     *
     * 这个题目，没有找到leetcode对应的。
     */
    public int getOnsecutiveMaxLen(int[] arr) {
        int maxCount = 0;
        for (int i = 0; i < arr.length; i++) {
            int preCount = 1;
            if (((i + 1) < arr.length - 1) && (arr[i] != arr[i + 1])) {
                i++;
            } else {
                maxCount++;
            }
            if (maxCount < preCount) {
                maxCount = preCount;
            }
        }
        return maxCount;
    }

    /**
     * 题目13：长度最小的子数组, 找出该数组中满足其和 ≥ s 的长度最小的子数组,  滑动窗口方法, 因为是正整数，可以用滑动窗口。
     * <a href="https://leetcode.cn/problems/2VG8Kg/description/">...</a>
     * 思路：左右两个指针移动，指针之间的距离就像一个窗口一样，发现窗口内的和比目标值大时，就left ++, 小时就right++
     * <p>
     * Space O(1), time O(n)
     */
    public int minSubArrayLen(int target, int[] nums) {
        int minLen = nums.length + 1;
        int l = 0;
        int r = 0;
        int sum = 0;
        while (l < nums.length) {
            if (r < nums.length && sum < target) {
                sum += nums[r++];
            } else {
                sum -= nums[l++];
            }

            if (sum >= target) {
                minLen = Math.min(minLen, r - l);
            }
        }

        if (minLen == nums.length + 1) {
            return 0;
        }

        return minLen;
    }

    /**
     * 题目13： <a href="https://leetcode.cn/problems/maximum-average-subarray-i/">...</a>
     * 给一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
     * <p>
     * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
     *
     * 输入：nums = [1,12,-5,-6,50,3], k = 4
     * 输出：12.75
     * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
     *
     * 关键的是：sum_i = sums_i-1 - nums[i-k]+ nums[i] 这样可以节省time
     *
     */
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i = k; i < nums.length ; i++) {
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, sum);
        }

        return 1.0 * maxSum / k;
    }

    /**
     * 题目14: 删除有序数组中的重复项
     * 分别用两个指针，i,j 去移动，不相等的话，指针i++
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
