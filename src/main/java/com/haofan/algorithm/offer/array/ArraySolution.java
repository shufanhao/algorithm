package com.haofan.algorithm.offer.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
     *
     * <a href="https://leetcode.cn/problems/3sum/">...</a>
     * 输入一个数组，如何找出数组中所有和为0的3个数字的三元组？需要注意的是，返回值中不得包含重复的三元组。
     * 例如，在数组[-1，0，1，2，-1，-4]中有两个三元组的和为0，它们分别是[-1，0，1]和[-1，-1，2]，其中[-1, -1, 2]是不行的。
     * 不包含重复的数组的意思就是：三个数字中不能相同。在三数之和确定的情况下，只要确定第一个和第二个数不相同即可。
     * <p>
     * 解法：
     * 通过遍历数组，从第一个数i开始，两个指针j, k分别从i+1,n-1往中间移动，去找到满足sum = nums[i] + nums[j] + nums[k] == 0 的所有组合
     * j K移动逻辑，几居室 sum > 0; k 左移，sum < 0; j右移。sum = 0，则找到。
     * <p>
     * 其实是和上面题目的逻辑是类似的
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length <= 2) {
            return result;
        }
        Arrays.sort(nums);
        int i, p, q, reverse;
        for (i = 0; i < nums.length - 2; i++) {
            reverse = -nums[i];
            p = i + 1;
            q = nums.length - 1;
            while (p < q) {
                if ((nums[p] + nums[q]) == reverse) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[p]);
                    temp.add(nums[q]);
                    result.add(temp);
                    p++;
                    // 这个地方要注意，为了防止重复，一定要加
                    while ((p < q) && (nums[p - 1] == nums[p])) {
                        p++;
                    }
                    q--;
                } else if ((nums[p] + nums[q]) < reverse) {
                    p++;
                    // 这个地方要注意，为了防止重复，一定要加
                    while ((p < q) && (nums[p - 1] == nums[p])) {
                        p++;
                    }
                } else {
                    q--;
                }
            }
            // 这个地方要注意，为了防止重复，一定要加
            while ((i + 1) < nums.length && (nums[i] == nums[i + 1])) {
                i++;
            }
        }
        return result;
    }

    public List<List<Integer>> threeSumViaMap(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        // init map
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            // skip duplicate
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < nums.length; j++) {
                // skip duplicate
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int target = -nums[i] - nums[j];
                // map.get(target) > j 很关键
                if (map.containsKey(target) && map.get(target) > j) {
                    res.add(List.of(nums[i], nums[j], target));
                }
            }
        }

        return res;
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
     *
     * <a href="https://leetcode.cn/problems/subarray-product-less-than-k/description/">...</a>
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
     * <p>
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
            // 这个地方是核心。为啥呢？
            // 对于当前的前缀和 preSum[i]，我们检查 preSum[i] - k 是否已经在哈希表中。如果在，这意味着从某个之前的位置 j（使得 preSum[j] = preSum[i] - k）到当前位置 i 的子数组的和为 k。
            res += map.getOrDefault(preSum[i] - k, 0);
        }
        return res;
    }


    /**
     * 面试题11：输入一个只包含0和1的子数组，求出0和1的个数相同的子数组
     * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
     * <p>
     * 解法：因为是输入只包含0和1，所有可以把0换成-1，这样题目就变成找到含有相同数量的-1和1的最长连续子数组，也就是和为0。这就和上上面的题目类似了。
     * <p>
     * 输入是{0,1,0} 输出是2，time: o(n), space: o(n)
     */
    public int findMaxLength(int[] nums) {
        // key: sum, value: index of num.
        Map<Integer, Integer> sumToIndex = new HashMap<>();
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
     * <a href="https://leetcode.cn/problems/find-the-duplicate-number/solutions/261119/xun-zhao-zhong-fu-shu-by-leetcode-solution/">...</a>
     * <p>
     * 面试题2：数组中的重复数字 <a href="https://github.com/todorex/Coding-Interviews/blob/master/notes/数组中重复的数字.md">
     * <p>
     * 输入{2, 3, 1, 0, 2, 5} 找出2, 算法时间复杂度O(n), 空间是O(1), 核心是：可以把数组的元素交换到应该放置的位置，比方：
     * 数组元素1，要放到index 1的位置，如果已经有元素了，说明就重复了。
     */
    public int findDuplicate(int[] array) {
        if (array.length == 0) {
            return -1;
        }

        for (int i = 0; i < array.length; i++) {
            // 首先比较这个数字（用m表示）是不是等于i，如果是，则继续扫描下一个
            while (array[i] != i) {
                // 如果不是，就拿它和第m个数字比较，如果它和第m个数字相等，就找到了一个重复的数字；
                // 如果不相等，就把第i个数字与第m个数字交换，把m放到属于它的位置，接下来再重复这个比较，交换的过程，直到我们发现一个重复的数字。
                if (array[i] == array[array[i]]) {
                    return array[i];
                }

                int temp = array[i];
                array[i] = array[temp];
                array[temp] = temp;
            }
        }
        return -1;
    }

    /**
     * 面试题 238: 除自身以外数组的乘积
     * <p>
     * 给你一个整数数组 nums，返回数组 answer，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     * <p>
     * 解题：
     * 1. 左右乘积列表。索引左侧所有数字的乘积和右侧所有数字的乘积（即前缀与后缀）相乘得到答案。
     * 所以先初始化两个数组，L, R。L[i] 代表i左侧所有的乘积，R[i]是i右侧所有的乘积。
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
            R[i] = nums[i + 1] * R[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            answer[i] = L[i] * R[i];
        }

        return answer;
    }

    /**
     * 面试题240: 搜索二维矩阵II
     * <p>
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
     * <p>
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * <p>
     * 解法：
     * 1. 暴力解法，time: O(m*n)
     * 2. 二分查找，因为都是排序的。
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // 暴力解法
        for (int i = 0; i < matrix.length; i++) {
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
        for (int[] row : matrix) {
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

    /**
     * 面试题448: 找到所有数组中消失的数字
     * <p>
     * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
     * <p>
     * 主要难点是在于空间复杂度必须是O(1)。
     * *
     * 思路1
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        // 把nums 转成set，然后遍历1到n，拿出不在set的数值
        Set<Integer> set = new HashSet<>();
        for (Integer num : nums) {
            set.add(num);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 思路2：
     * 原地修数组
     * 解题：<a href="https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/solutions/1/yi-zhang-dong-tu-bang-zhu-li-jie-yuan-di-uign/">...</a>
     * <p>
     * 长度为 N 的数组可以用来统计 1 N 各数字出现的次数；题目给出的数组的长度正好为 N，所以可以原地修改数组实现计数。
     * <p>
     * 当前元素是 nums[i]，那么我们把第 nums[i]−1 位置的元素 乘以 -1，表示这个该位置出现过。当然如果 第 nums[i]−1 位置的元素已经是负数了，
     * 表示 nums[i] 已经出现过了，就不用再把第 nums[i]−1 位置的元素乘以 -1。最后，对数组中的每个位置遍历一遍，如果 i 位置的数字是正数，说明 i 未出现过。
     * 就是利用了nums[i] 可以是数组的index
     */
    public List<Integer> findDisappearedNumbers1(int[] nums) {
        for (int num : nums) {
            // 数组是1到n, 而数组的索引是0到n-1, 所以要减去-1
            if (nums[Math.abs(num) - 1] > 0) {
                nums[Math.abs(num) - 1] *= -1;
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }

    /**
     * 面试题560: 和为k的子数组
     *
     * <a href="https://leetcode.cn/problems/subarray-sum-equals-k/description/">...</a>
     * <p>
     * time： O(n2)
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            // 从后往前找，这样可以包括了是否是等于自己的情况
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 比较难想吧:
     * <p>
     * pre[i] 为 [0..i] 里所有数的和
     * pre[i] = pre[i - 1] + nums[i]
     * <p>
     * [j..i] 这个子数组和为 k, 可以转化为:
     * pre[i] - pre[j -1] == k
     * 所以pre[j - 1] == pre[i] - k
     * <p>
     * 所以：建立hash表，和为key,出现次数为value，从左到右更新map.
     */
    public int subarraySum1(int[] nums, int k) {
        int count = 0, pre = 0;
        // key: 是和，value: 次数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            // 计算pre的值，前i的和
            pre += nums[i];
            if (map.containsKey(pre - k)) {
                count += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }

    /**
     * 题目581: 最短无序连续子数组。
     * <a href="https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/description/">...</a>
     * <p>
     * 输入：nums = [2,6,4,8,10,9,15]
     * 输出: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     * 思路：
     * 1. 先排序，然后和原数组比较，从前往后和从后往前，第一个不同的记录下来即可。时间复杂度O(nlogn)
     * 2. O(n)，从左到右循环，记录最大值Max，若nums[i] < max, 则表明i需要调整，循环结束，记录需要调整的
     * 最大位置i为high,同理，从右到左循环，记录最小值为min，若nums[i] > min，则表明i需要调整，循环结束，最小位置i为low.
     */
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int high = 0, low = len - 1, max = nums[0], min = nums[len - 1];

        for (int i = 1; i < len; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[len - 1 - i]);
            if (nums[i] < max) high = i;
            if (nums[len - 1 - i] > min) low = len - 1 - i;
        }

        return high > low ? high - low + 1 : 0;
    }

    // 每日一题：https://leetcode.cn/problems/minimum-length-of-anagram-concatenation/
    // 枚举方法
    public int minAnagramLength(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int len = s.length();

        // 遍历所有可能的分割长度 i
        for (int i = 1; i <= len; i++) {
            if (len % i != 0) { // 必须是 len 的因子
                continue;
            }

            // 检查是否可以用长度 i 分割成若干变位词
            if (isValid(s, i)) {
                return i; // 找到最小长度，直接返回
            }
        }
        return len;
    }

    private boolean isValid(String s, int length) {
        int[] baseFreq = new int[26]; // 记录第一个子串的字符频次
        for (int i = 0; i < length; i++) {
            baseFreq[s.charAt(i) - 'a']++;
        }

        // 滑动窗口验证后续子串是否与第一个子串匹配, 只需要和第一个子串比较即可。
        for (int start = length; start < s.length(); start += length) {
            int[] currentFreq = new int[26];
            for (int i = start; i < start + length; i++) {
                currentFreq[s.charAt(i) - 'a']++;
            }

            if (!Arrays.equals(baseFreq, currentFreq)) {
                return false;
            }
        }
        return true;
    }

    // https://leetcode.cn/problems/sort-the-students-by-their-kth-score/description/
    // 自己写的：
    public int[][] sortTheStudents(int[][] score, int k) {
        if (score[0].length < k) {
            return score;
        }

        // key score, value: student
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < score.length; i++) {
            map.put(score[i][k], i);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getKey() - o1.getKey();
            }
        });

        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        sortedMap.forEach((key, valve) -> System.out.println(key + ": " + valve));

        int[][] res = new int[score.length][score[0].length];

        int row = 0;
        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            // key: score
            // value: student
            for (int col = 0; col < score[0].length; col++) {
                res[row][col] = score[entry.getValue()][col];
            }
            row++;
        }

        return res;
    }

    public int[][] sortTheStudentsII(int[][] score, int k) {
        // 官方解题:
        Arrays.sort(score, (u, v) -> v[k] - u[k]);
        return score;
    }

    // https://leetcode.cn/problems/sort-integers-by-the-power-value/
    // 自己做出来。
    public int getKth(int lo, int hi, int k) {
        Map<Integer, Integer> map = new HashMap();
        int num = lo;
        while (num <= hi) {
            map.put(num, getPower(num));
            num++;
        }
        // sort by value

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                if (o1.getValue() != o2.getValue()) {
                    return o1.getValue() - o2.getValue();
                } else {
                    return o1.getKey() - o2.getKey();
                }
            }
        });

        return list.get(k - 1).getKey();
    }

    private int getPower(int num) {
        int i = 0;
        while (num > 1) {
            if (num % 2 == 0) {
                num = num / 2;
            } else {
                num = 3 * num + 1;
            }
            i++;
        }
        return i;
    }

    // leetcode 官方解题：https://leetcode.cn/problems/sort-integers-by-the-power-value/solutions/168355/jiang-zheng-shu-an-quan-zhong-pai-xu-by-leetcode-s/
    private int getPowerII(int num) {
        if (num == 1) {
            return 0;
        } else if ((num & 1) != 0) {
            return getPower(num * 3 + 1) + 1;
        } else {
            return getPower(num / 2) + 1;
        }
    }

    // https://leetcode.cn/problems/exam-room/solutions/2036518/kao-chang-jiu-zuo-by-leetcode-solution-074y/
    // 有些难度，不是很理解。通过TreeSet
    static class ExamRoom {
        // 考场中最后一个座位的编号（n-1）。
        private int R;
        // 一个有序集合，用于存储当前已被占用的座位编号。TreeSet, 自动保持元素的升序排列。在这里用于高效查找座位的邻居位置
        private TreeSet<Integer> seats = new TreeSet<>();

        public ExamRoom(int n) {
            R = n - 1;
        }

        public int seat() {
            // q 是上一个座位编号；s 是当前选择的最佳座位编号。
            int q = 0, s = 0;
            // max 记录当前最大距离。如果考场为空，选择最后一个座位编号（R）。
            int max = seats.isEmpty() ? R : seats.first();
            // 遍历已占用的座位集合。
            for (int p : seats) {
                //  计算当前两个座位之间的中点距离。
                int avg = (p - q) / 2;
                // 如果当前距离比最大距离大，则更新最佳座位编号。
                if (max < avg) {
                    s = q + (max = avg);
                }
                q = p;
            }
            // 检查最后一个座位到最后一名学生之间的距离。
            if (max < R - q) {
                s = R;
            }
            seats.add(s);
            return s;
        }

        public void leave(int p) {
            seats.remove(p);
        }
    }

    // https://leetcode.cn/problems/find-occurrences-of-an-element-in-an-array/
    // nums: [1,3,1,7], queries = [1,3,2,4], x = 1
    // 自己做出来的
    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        if (queries == null || queries.length == 0 || nums == null || nums.length == 0) {
            return new int[]{-1};
        }

        // store: x index of array: {0, 2}
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == x) {
                list.add(i);
            }
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            if (queries[i] > list.size()) {
                res[i] = -1;
            } else {
                res[i] = list.get(queries[i] - 1);
            }
        }
        return res;
    }

    // https://leetcode.cn/problems/remove-element/?envType=study-plan-v2&envId=top-interview-150
    // Input: nums = [0,1,2,2,3,0,4,2], val = 2
    // Output: 5, nums = [0,1,4,0,3,_,_,_]
    public int removeElement(int[] nums, int val) {
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            // 一定是不等于。
            if (nums[i] != val) {
                nums[cur++] = nums[i];
            }
        }
        return cur;
    }

    // https://leetcode.cn/problems/split-the-array/
    public boolean isPossibleToSplit(int[] nums) {
        int[] count=new int[101];
        for (int num : nums) {
            count[num]++;
            if(count[num]>2)return false;
        }
        return true;
    }

    // https://leetcode.cn/problems/h-index/?envType=study-plan-v2&envId=top-interview-150
    // 有点儿坑，通过逆序，就比较容易理解。
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0, i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }

    // https://leetcode.cn/problems/minimum-operations-to-exceed-threshold-value-i/
    public int minOperations(int[] nums, int k) {
        return (int) Arrays.stream(nums).filter(num -> num < k).count();
    }

    // https://leetcode.cn/problems/gas-station

    /**
     * - 核心思路是从某个加油站出发，如果能够到达下一个加油站且剩余油量更多或者刚好够，就继续前进；如果剩余油量不足以到达下一个加油站，就从下一个加油站重新开始尝试。
     *
     * 有点儿不是很理解呢。
     * - 具体步骤如下：
     *
     * - 首先初始化一些变量，包括总油量、当前油量、起始加油站索引等。
     *
     * - 然后遍历加油站数组，计算从当前站到下一站后的剩余油量（当前站油量减去到下一站的油耗），并更新总油量和当前油量。
     *
     * - 如果在某一站剩余油量小于0，说明从当前起始站出发无法完成行程，将起始站索引更新为下一站，同时重置当前油量为0。
     *
     * - 通过这样不断尝试和调整起始站，最终如果能够遍历完所有加油站且总油量大于等于0，就找到了可行的起始站；否则返回 -1。
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int currentGas = 0;
        int startStation = 0;

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i] - cost[i];
            currentGas += gas[i] - cost[i];

            if (currentGas < 0) {
                startStation = i + 1;
                currentGas = 0;
            }
        }

        return totalGas >= 0? startStation : -1;
    }

    // https://leetcode.cn/problems/candy/?envType=study-plan-v2&envId=top-interview-150

    /**
     * 思路：两次遍历
     * 左规则：当 ratings[i−1]<ratings[i] 时，i 号学生的糖果数量将比 i−1 号孩子的糖果数量多。
     * 右规则：当 ratings[i]>ratings[i+1] 时，i 号学生的糖果数量将比 i+1 号孩子的糖果数量多。
     */
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            if (i >0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }

        int right = 0, ret = 0;
        for (int i = n -1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right ++;
            } else {
                right = 1;
            }
            // 取最大值
            ret += Math.max(left[i], right);
        }
        return ret;
    }
}
