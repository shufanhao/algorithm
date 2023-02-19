package com.haofan.algorithm.leetcodecn.easy.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
     * 题目1.1: 从排序数组中删除重复项, 快慢指针
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

    /**
     * 题目1.2：颜色排序
     * http://www.cnblogs.com/grandyang/p/4341243.html
     * 思路：
     * 1. 遍历一遍原数组，分别记录0，1，2的个数， 更新原数组，按个数分别赋上0，1，2
     * 2. 定义两个指针，start,end; 从头开始遍历数组，如果end值遇到0，则交换改值和start指向的值，
     * 并将start后移一位。若start值遇到2，则交换改值和end指向的值，并将end前移一位，若遇到1则继续遍历
     */
    public static void sortColors(int nums[]) {
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
     * 题目2.1：买卖股票的最佳时机 II(直接求和)
     * 其实就是就一个和：下一天的价格减去前一天的价格差，这个差求和
     **/
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                sum += prices[i + 1] - prices[i];
            }
        }
        return sum;
    }

    /**
     * 题目2.2：买卖股票的最佳时机 III(动态规划)
     * 设计一个算法计算获取的最大利润，只能完成一笔交易。
     * 其实最小买入，最大卖出
     */
    public static int maxProfitIII(int[] prices) {
        int result = 0;
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int minBuy = prices[0];
        for (int i = 1; i < prices.length; i++) {
            result = Math.max(result, prices[i] - minBuy);
            minBuy = Math.min(prices[i], minBuy);
        }
        return result;
    }

    /**
     * 题目3：旋转数组
     * 解法1：利用o(n)的空间复杂度
     * 解法2：空间复杂度是o(1)，先反转前n-k个元素，再反转后k个元素，再整个数组反转
     */
    public static void rotate(int[] nums, int k) {
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

        // 解法2：
        reverse(nums, 0, nums.length - 1 - k);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = tmp;
        }
    }

    /**
     * 题目4：存在重复
     * 1. 解法：将数组放到HashMap中，然后检查是否有key，如果有说明，已经存放过。
     * 2. 解法：先将数组排序，然后比较前后2个元素
     */
    public static boolean containsDuplicate(int[] nums) {
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
     * 题目 5： 只出现一次的数字
     * 任何一个数字异或它自己都等于0。也就是说，如果我们从头到尾依次异或数组中的每一个数字，
     * 那么最终的结果刚好是那个只出现依次的数字，因为那些出现两次的数字全部在异或中抵消掉了。
     */
    public static int singleNumber(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            temp = temp ^ nums[i];
        }
        return temp;
    }

    /**
     * 题目 6：两个数组的交集 II
     * 先将两个数组排序，然后两个指针分别指向数组1，数组2，根据两个数组的元素大小，决定是否移动指针
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len = nums1.length > nums2.length ? nums2.length : nums1.length;
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
     * 题目7： 加一
     */
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i > 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = 1;
        return newDigits;
    }

    /**
     * 题目8：移动0，类似第一个题目：从排序数组中删除重复的项
     */
    public static int[] moveZeroes(int[] nums) {
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
     * 题目9： 两数之和
     * 1. 常规思路：o(n的平方) 的时间复杂度，类似于冒泡排序
     * 2. 简单思路：将数组的元素和对应的index，存到Map中
     * 用target 减去 数组的每一个元素，然后将结果在map中检查是否有对应的key
     * 如果有，则返回
     */
    public static int[] twoSum(int[] nums, int target) {
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
     * 有效的数独：https://blog.csdn.net/biezhihua/article/details/79648015
     * 1. 抽象出三个二维数组，rawFlag[x][x], 表示在第x行中，出现过数字x, colFlag[][]
     * cellFlag[][] 表示在第几个方阵中出现过数字x
     * 2. 使用3*(i/3) + j/3 i 代表行的增加，j代表列的增加，可以知道现在处于第几个方阵
     * 3. 如果数字是char 型， 则 int c = x - '1', c 就是对应的数字
     */
    public static boolean isValidSudoku(char[][] board) {
        int len = 9;
        boolean[][] rowFlag = new boolean[len][len];
        boolean[][] colFlag = new boolean[len][len];
        boolean[][] cellFlag = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (board[i][j] >= '1' && board[i][j] <= '9') {
                    int c = board[i][j] - '1';
                    if (rowFlag[i][c] || colFlag[c][j] || cellFlag[3 * (i / 3) + j / 3][c]) {
                        return false;
                    }
                    rowFlag[i][c] = true;
                    colFlag[c][i] = true;
                    cellFlag[3 * (i / 3) + j / 3][c] = true;
                }
            }
        }
        return true;
    }

    /**
     * 题目11：
     * 旋转图像：https://blog.csdn.net/biezhihua/article/details/79653162
     * 解法：因为要求不能新的矩阵，所以要看看没有规律，直接调换元素。
     * 先调换所有对角元素，再调换列元素，就得到顺时针旋转90度后的元素。
     */
    public static void rotate(int[][] matrix) {

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
     * 解法：提供变量去记录之前的最大连续子序列，然后用记录的maxCount去比较即可
     */
    public static int getOnsecutiveMaxLen(int arr[]) {
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

    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    /**
     * 题目13：长度最小的子数组, 找出该数组中满足其和 ≥ s 的长度最小的子数组,  滑动窗口方法
     * 思路：左右两个指针移动，指针之间的距离就像一个窗口一样，发现窗口内的和比目标值大时，就left ++, 小时就right++
     */
    public static int minSubArrayLen(int s, int[] nums) {
        int l = 0; // left
        int r = 0;// right
        int sum = 0;
        int minLen = nums.length + 1; //不存在的case
        while (l < nums.length) {
            if (r < nums.length && sum < s) {
                sum += nums[r++];
            } else {
                sum -= nums[l++];
            }
            if (sum >= s) {
                minLen = Math.min(minLen, r - l);
            }
        }
        if (minLen == nums.length + 1) {
            return 0;
        }
        return minLen;
    }

    /**
     * 题目13：求最大平均值的连续子序列的最大平均值, 目标值是k
     * 解法：
     * 如果K=1, 结果肯定是最大的那个值
     * 如果K>1, 结果肯定是连续2个的序列比3个的子序列的平均值要大
     */
    public static int maxSubArrayAverage(int k, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (k > nums.length) {
            return 0;
        }
        if (k == 1) {
            Arrays.sort(nums);
            return nums[nums.length - 1];
        } else {
            // if k > 1, 那肯定是连续2个的序列比3个的子序列的平均值要大
            int sum = 0;
            int tempMax = nums[0] + nums[1];
            for (int j = 2; j < nums.length; j++) {
                sum = nums[j - 1] + nums[j];
                tempMax = Math.max(tempMax, sum);
            }
            return (tempMax / 2);
        }

    }


    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        // 题目1.2
        //int arr_13[] = {1,1,1,0,0,0,1,2,2};
        int arr_1_2[] = {2, 0, 1};
        sortColors(arr_1_2);
        System.out.println("题目1.2：" + Arrays.toString(arr_1_2));
        // 题目2.1：
        int[] prices_1 = {1, 2, 3, 4, 5};
        System.out.println("题目2.1：买卖股票的最佳时机：" + maxProfit(prices_1));

        // 题目2.2：
        int[] prices_2 = {7, 1, 5, 3, 6, 4};
        System.out.println("题目2.2：买卖股票的最佳时机：" + maxProfitIII(prices_2));

        // 题目3：
        int[] rotate = {1, 2, 3, 4, 5, 6, 7};
        rotate(rotate, 2);
        System.out.println("题目3：旋转数组： " + Arrays.toString(rotate));

        // 题目4：
        System.out.println("题目4：存在重复： " + (containsDuplicate(nums)));

        // 题目5：
        int single[] = {4, 1, 2, 1, 2};
        System.out.println("题目5：只出现一次的数字： " + singleNumber(single));

        // 题目6：
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println("题目6： 两个数组的交集： " + Arrays.toString(intersect(nums1, nums2)));

        // 题目7：
        int[] plusOne = {1, 2, 9};
        System.out.println("题目7： 加一： " + Arrays.toString(plusOne(plusOne)));

        // 题目8：
        int[] move = {0, 1, 0, 3, 12};
        System.out.println("题目8： 移动0 " + Arrays.toString(moveZeroes(move)));

        // 题目9：
        int[] twosSumArr = {2, 7, 11, 15};
        System.out.println("题目9： 两数之和 " + Arrays.toString(twoSum(twosSumArr, 9)));

        // 题目10：
        char[][] a = {
                {'.', '8', '7', '6', '5', '4', '3', '2', '1'},
                {'2', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'3', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'4', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'5', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'6', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'8', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'9', '.', '.', '.', '.', '.', '.', '.', '.'}
        };
        System.out.println("题目10： 有效的数独 " + isValidSudoku(a));

        // 题目11：
        int[][] rotateArr = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotate(rotateArr);
        for (int i = 0; i < rotateArr.length; i++) {
            System.out.println("题目11： 旋转图像 " + Arrays.toString(rotateArr[i]));
        }

        // 题目12：
        int arr[] = {1, 1, 2, 2, 3, 3, 1, 1, 1, 1};
        System.out.println("题目12：输出数组中最大的连续子序列的长度 " + getOnsecutiveMaxLen(arr));

        // 题目13：
        int arr_13[] = {2, 3, 1, 2, 4, 3};
        System.out.println("题目13：长度最小的子数组 " + minSubArrayLen(7, arr_13));

        // 题目14：
        int arr_14[] = {-1, 1, 2, 4};
        System.out.println("题目14：求最大平均值的连续子序列的最大平均值 " + maxSubArrayAverage(1, arr_14));

        System.out.println("题目14：求最大平均值的连续子序列的最大平均值 " + maxSubArrayAverage(3, arr_14));

    }
}
