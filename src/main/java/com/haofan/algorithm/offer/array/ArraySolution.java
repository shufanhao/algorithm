package com.haofan.algorithm.offer.array;

import java.util.*;

public class ArraySolution {
    /**
     * 题目6：排序数组中的两个数字之和
     * 输入一个递增排序的数组和一个值k，请问如何在数组中找出两个和为k的数字并返回它们的下标？
     * 假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。例如，输入数组[1，2，4，6，10]，k的值为8，
     * 数组中的数字2与6的和为8，它们的下标分别为1与3。
     * <p>
     * 解法：
     * 1. 空间换时间。把数组中每个元素，以及index，都放入到Map中，然后遍历，map，如果发现有k-i的值则返回。time O(N)，space O(N)
     * 2. 双指针，因为是排序数组，从left, right指针，left + right的值如果大于K，则right 左移，反之右移，直到出现和等于8的值。time O(N), space O(1)
     */
    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }

            map.put(nums[i], i);
        }

        return null;
    }

    public int[] twoSum2(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                // 找到正确的和，立即返回索引
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }

    /**
     * 题目7: 数组中和为0的3个数字
     * 输入一个数组，如何找出数组中所有和为0的3个数字的三元组？需要注意的是，返回值中不得包含重复的三元组。
     * 例如，在数组[-1，0，1，2，-1，-4]中有两个三元组的和为0，它们分别是[-1，0，1]和[-1，-1，2]，其中[-1, -1, 2]是不行的。
     * 不包含重复的数组的意思就是：三个数字中不能相同。在三数之和确定的情况下，只要确定第一个和第二个数不相同即可。
     * <p>
     * 解法：
     * 通过遍历数组，从第一个数i开始，两个指针j, k分别从i+1,n-1往中间移动，去找到满足sum = nums[i] + nums[j] + nums[k] == 0 的所有组合
     * j K移动逻辑，几居室 sum > 0; k 左移，sum < 0; j右移。sum = 0，则找到。
     *
     * 其实是和上面题目的逻辑是类似的
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int j = i + 1, k = n - 1;
            while (j < k) {
                while (j > i + 1 && j < n && nums[j] == nums[j - 1]) j++;
                if (j >= k) break;
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                } else if (sum > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return ans;
    }

    /**
     * 题目8: 和大于或等于k的最短子数组
     * 输入一个正整数组成的数组和一个正整数k，请问数组中和大于或等于k的连续子数组的最短长度是多少？如果不存在所有数字之和大于或等于k的子数组，
     * 则返回0。例如，输入数组[5，1，4，3]，k的值为7，和大于或等于7的最短连续子数组是[4，3]，因此输出它的长度2
     * <p>
     * 解法：
     * 还是双指针，同时移动
     * 下面以数组[5，1，4，3]为例一步步分析用两个指针找出和大于或等于7的最短子数组的过程。
     * 首先，指针P1和P2都指向数组中的第1个数字5。此时子数组中只有一个数字5，因此子数组中的所有数字之和也是5，小于7。
     * 然后把指针P2向右移动一步指向数字1。此时子数组中包含两个数字，即5和1，它们的和为6，仍然小于7。因此，再把指针P2向右移动一步指向数字6，此
     * 时数组中包含3个数字，即5、1和4，它们的和是10，大于7。由此找到了一个和大于7的子数组，它的长度是3
     * <p>
     * 尝试把指针P1向右移动一步，确定是否能找到更短的符合要求的子数组。移动指针P1之后，子数组中包含两个数字，即1和4，小于7。然后将指针P2向右移动一步指向数字3。
     * 此时子数组中包含3个数字，即1、4和3，它们的和为8。此时又找到了和大于7的子数组，它的长度也是3。
     * 然后p1可以进一步向右移动.
     */
    public int minSunArrayLen(int k, int[] nums) {
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (left <= right && sum >= k) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left++];
            }
        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    /**
     * 面试题9：乘积小于k的子数组
     * 输入一个由正整数组成的数组和一个正整数k，请问数组中有多少个数字乘积小于k的连续子数组？例如，输入数组[10，5，2，6]，k的值为100，
     * 有8个子数组的所有数字的乘积小于100，它们分别是[10]、[5]、[2]、[6]、[10，5]、[5，2]、[2，6]和[5，2，6]
     * <p>
     * 和题目4一样，也是双指针，但是这个题目稍微trick一点
     * 解法：
     * 1. 两个指针l，r，l是用于乘积大于等于k的时候，删除乘积中第一个数，r用于遍历数组的
     * 2. 两个指针，r, r 初始值是0
     * 3. 遍历数组计算乘积total
     * 4. 每遍历一个数，那么将total和K进行比较，如果total 大于等于k, total 不断除以nums[l++]，直到total小于k
     * 5. 此时当前指针r对应的乘积小于k的情况有r-l+1种。
     *
     * Time: O(n), space: O(1)
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int res = 0, total = 1, r, l = 0;
        for (r = 0; r < nums.length; r++) {
            total *= nums[r];
            while (total >= k) {
                total /= nums[l++]; //如果total大于等于k的时候，l指针后移，此时也满足了如果第一个数大于等于k的时候，res最后等于0
            }
            // l 和 r 之间的就是有多少个子数组。
            res += r - l + 1;
        }
        return res;
    }

    /**
     * 面试题10： 和为k的子数组
     * <p>
     * 输入一个整数数组和一个整数k，请问数组中有多少个数字之和等于k的连续子数组？
     * 例如，输入数组[1，1，1]，k的值为2，有2个连续子数组之和等于2。
     * <p>
     * 需要注意的是preSum[i] - preSum[j] = K
     * 解法： <a href="https://www.youtube.com/watch?v=8C3GZEncJjU"> 视频解释比较清楚</a>
     * 1. 先遍历一次数组，计算数组中每一个元素对应的前缀和（即在数组中该元素以及它前面的所有元素的和为多少），构建前缀和数组。
     * 2. 为了避免二次遍历，我们构建一个哈希表，其key为前缀和，value为该前缀和出现的次数。
     * 3. 核心思想：要找到有多少符合preSum[j] - preSum[i] = K 的情况。i为子数组左边界，j为子数组右边界。也就是说我们在遍历到位置j时，
     * 想知道在之前有多少个位置，符合preSum[i] = preSum[j] - K。即前缀和preSum[j]出现了多少次，而这恰好可以直接从哈希表中得到。
     */
    // 3, 4, 7, 2. 7
    public int subArraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] preSum = new int[nums.length + 1];
        int res = 0;
        preSum[0] = 0;
        map.put(0, 1);
        for (int i = 1; i <= nums.length; i++) {
            // 创建一个数组来记录所有的前缀和
            preSum[i] = preSum[i - 1] + nums[i - 1];
            // 创建一个hashMap, key 是前缀和，value 是出现的次数。因为是非正数，注意。。
            if (!map.containsKey(preSum[i])) {
                map.put(preSum[i], 1);
            } else {
                map.put(preSum[i], map.get(preSum[i]) + 1);
            }
            // 这个地方是核心。
            res += map.getOrDefault(preSum[i] - k, 0);
        }
        return res;
    }


    /**
     * 面试题11：输入一个只包含0和1的子数组，求出0和1的个数相同的子数组
     * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
     *
     * 解法：因为是输入只包含0和1，所有可以把0换成-1，这样题目就变成找到含有相同数量的-1和1的最长连续子数组，也就是和为0。这就和上上面的题目类似了。
     *
     * 输入是{0,1,0} 输出是2，time: o(n), space: o(n)
     */
    public int findMaxLength(int[] nums) {
        // key: sum, value: index of num.
        Map<Integer, Integer> sumToIndex= new HashMap<>();
        sumToIndex.put(0, -1);
        int sum = 0;
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 0 ? -1 : 1;
            // map 不包含这个key
            if (!sumToIndex.containsKey(sum)) {
                sumToIndex.put(sum, i);
            } else {
                // 已经包含这个key, 那么说明 i - 之前在index位置sum的 的和为0
                maxLength = Math.max(maxLength, i - sumToIndex.get(sum));
            }
        }
        return maxLength;
    }
    /**
     * 剑指offer 2
     * 面试题2：数组中的重复数字 <a href="https://github.com/todorex/Coding-Interviews/blob/master/notes/">...</a>数组中重复的数字.md
     * <p>
     * 输入{2, 3, 1, 0, 2, 5} 找出2, 算法时间复杂度O(n), 空间是O(1), 核心是：
     */
    public boolean findDuplicate(int[] array) {
        // 杜绝数组为空
        if (array.length == 0) {
            return false;
        }
        // 杜绝数组有非法数字
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0 || array[i] > array.length - 1) {
                return false;
            }
        }

        for (int i = 0; i < array.length; i++) {
            while (array[i] != i) {
                if (array[i] == array[array[i]]) {
                    System.out.println("duplicate number: " + array[i]);
                    return true;
                }

                int temp = array[i];
                array[i] = array[temp];
                array[temp] = temp;
            }
        }
        return false;
    }

    /**
     * 面试题 238: 除自身以外数组的乘积
     *
     * 给你一个整数数组 nums，返回数组 answer，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     *
     * 解题：
     * 1. 左右乘积列表。索引左侧所有数字的乘积和右侧所有数字的乘积（即前缀与后缀）相乘得到答案。
     *    所以先初始化两个数组，L, R。L[i] 代表i左侧所有的乘积，R[i]是i右侧所有的乘积。
     */
    public int[] productExceptSelf(int[] nums) {
        int[] L = new int[nums.length];
        int[] R = new int[nums.length];

        int[] answer = new int[nums.length];

        // L[i] 为索引 i 左侧所有元素的乘积
        // 因为第一个元素的左侧没有值
        L[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            // 通过这种方式，来避免 duplicate的计算。
            L[i] = nums[i - 1] * L[i - 1];
        }

        // R[i] 为索引 i 右侧所有元素的乘积
        R[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            // 通过这种方式，来避免 duplicate的计算.
            R[i] = nums[i+1] * R[i+1];
        }

        for (int i = 0; i<nums.length; i++) {
            answer[i] = L[i] * R[i];
        }

        return answer;
    }

    /**
     * 面试题240: 搜索二维矩阵II
     *
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
     *
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     *
     * 解法：
     * 1. 暴力解法，time: O(m*n)
     * 2. 二分查找，因为都是排序的。
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // 暴力解法
        for (int i=0; i< matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    // 二分查找, time: O(M*LogN), 因为二分查找的time是O(logn)
    public boolean searchMatrix1(int[][] matrix, int target) {
        for (int[] row: matrix) {
            int index = binarySearch(row, target);
            if (index >= 0) {
                return true;
            }
        }
        return false;
    }

    private int binarySearch(int[] num, int target) {
        if (num == null) {
            return -1;
        }

        int left = 0;
        int right = num.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (num[mid] < target) {
                left = mid + 1;
            } else if (num[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
