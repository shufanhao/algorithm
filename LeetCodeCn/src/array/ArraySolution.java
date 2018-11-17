package array;

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
     * 题目1: 从排序数组中删除重复项
     * 分别用两个指针，i,j 去移动，不相等的话，指针i++
     */
    public static int remoeDuplicates(int[] nums) {
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
     * 题目2：买卖股票的最佳时机
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
     * 题目8： 移动0
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
     * 2. 简单思路：将数组的元素和对赢的index，存到Map中
     * 用target 减去 数组的每一个元素，然后将结果在map中检查是否有对应的key
     * 如果有，则返回
     */
    public static int[] twoSum(int[] nums, int target) {
        if (nums == null) {
            return nums;
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
        return nums;
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
     */
    public static int getOnsecutiveMaxLen(int arr[]) {
        int maxCount = 0;
        for (int i=0; i<arr.length; i++) {
            int preCount = 1;
            if (((i+1) < arr.length -1 ) && (arr[i] != arr[i+1])) {
                i++;
            } else {
                maxCount ++;
            }
            if (maxCount < preCount) {
                maxCount = preCount;
            }
        }
        return maxCount;
    }
    /**
     * 题目12：https://blog.csdn.net/zhjali123/article/details/72871261
     * 输出数字矩形，输入N，输出：
     * 1， 2， 3， 4
     * 12，13，14，5
     * 11，16，15，6
     * 10，9， 8， 7
     * @param args
     */

    public static void main(String args[]) {
        // 题目1：
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println("题目1 从排序数组中删除重复项: " + remoeDuplicates(nums));

        // 题目2：
        int[] prices = {1, 2, 3, 4, 5};
        System.out.println("题目2：买卖股票的最佳时机：" + maxProfit(prices));

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
        int arr[] = {1,1,2,2,3,3,1,1,1,1};
        System.out.println("题目12：" + getOnsecutiveMaxLen(arr));
    }
}