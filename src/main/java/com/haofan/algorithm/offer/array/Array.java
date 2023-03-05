package com.haofan.algorithm.offer.array;

import java.util.*;

public class Array {
    /**
     * 题目1：排序数组中的两个数字之和
     * 输入一个递增排序的数组和一个值k，请问如何在数组中找出两个和为k的数字并返回它们的下标？
     * 假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。例如，输入数组[1，2，4，6，10]，k的值为8，
     * 数组中的数字2与6的和为8，它们的下标分别为1与3。
     * <p>
     * 解法：
     * 1. 空间换时间。把数组中每个元素，以及index，都放入到Map中，然后遍历，map，如果发现有k-i的值则返回。时间复杂度O(N)，空间O(N)
     * 2. 双指针，因为是排序数组，从left, right指针，left + right的值如果大于K，则right 左移，反之右移，直到出现和等于8的值。
     */
    public int[] twoSum1(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (map.containsKey(target - entry.getKey())) {
                return new int[]{entry.getValue(), map.get(target - entry.getKey())};
            }
        }
        return null;
    }

    public int[] twoSum2(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right && ((numbers[left] + numbers[right]) != target)) {
            if ((numbers[left] + numbers[right]) > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{left, right};
    }

    /**
     * 题目2：和大于或等于k的最短子数组
     * 输入一个数组，如何找出数组中所有和为0的3个数字的三元组？需要注意的是，返回值中不得包含重复的三元组。
     * 例如，在数组[-1，0，1，2，-1，-4]中有两个三元组的和为0，它们分别是[-1，0，1]和[-1，-1，2]，其中[-1, -1, 2]是不行的。
     * 不包含重复的数组的意思就是：三个数字中不能相同。在三数之和确定的情况下，只要确定第一个和第二个数不相同即可。
     * <p>
     * 解法：
     * 通过遍历数组，从第一个数i开始，连个指针j, k分别从i+1,n-1往中间移动，去找到满足sum = nums[i] + nums[j] + nums[k] == 0 的所有组合
     * j K移动逻辑，几居室 sum > 0; k 左移，sum < 0; j右移。sum = 0 ,找打
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
                } else if (sum < 0) {
                    j++;
                }
            }
        }
        return ans;
    }

    /**
     * 题目3: 和大于或等于k的最短子数组
     * 输入一个正整数组成的数组和一个正整数k，请问数组中和大于或等于k的连续子数组的最短长度是多少？如果不存在所有数字之和大于或等于k的子数组，
     * 则返回0。例如，输入数组[5，1，4，3]，k的值为7，和大于或等于7的最短连续子数组是[4，3]，因此输出它的长度2
     * <p>
     * 解法：
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
            while (left < right && sum >= k) {
                minLength = Math.min(minLength, right - left + 1);
                sum += nums[left++];
            }
        }
        return minLength;
    }

    /**
     * 题目4：乘积小于k的子数组
     * 输入一个由正整数组成的数组和一个正整数k，请问数组中有多少个数字乘积小于k的连续子数组？例如，输入数组[10，5，2，6]，k的值为100，
     * 有8个子数组的所有数字的乘积小于100，它们分别是[10]、[5]、[2]、[6]、[10，5]、[5，2]、[2，6]和[5，2，6]
     * <p>
     * 和题目4一样，也是双指针，但是这个题目稍微trick一点
     * 解法：
     * 1. 两个指针i，j，j是用于乘积大于等于k的时候，删除乘积中第一个数，i用于遍历数组的
     * 2. 两个指针，i, j 初始值是0
     * 3. 遍历数组计算乘积total
     * 4. 每遍历一个数，那么将total和K进行比较，如果total 大于等于k, total 不断除以nums[j++]，直到total小于k
     * 5. 此时当前指针i对应的乘积小于k的情况有i-j+1种。
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int res = 0, total = 1, i, j = 0;
        for (i = 0; i < nums.length; i++) {
            total *= nums[i];
            while (total >= k) {
                total /= nums[j++]; //如果total大于等于k的时候，j指针后移，此时也满足了如果第一个数大于等于k的时候，res最后等于0
            }
            res += i - j + 1;
        }
        return res;
    }

    /**
     * 题目5： 和为k的子数组
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
            res += map.getOrDefault(preSum[i] - k, 0);
        }
        return res;
    }
}
