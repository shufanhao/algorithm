package com.haofan.algorithm.leetcodecn.medium.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SortSolutionMedium {

    public static void main(String args[]) {
        SortSolutionMedium medium = new SortSolutionMedium();
//        int[] nums2 = new int[]{1, 1, 1, 2, 2, 3};
//        System.out.println("题目2：" + medium.topKFrequent(nums2, 2));
//
//        int[] nums3 = new int[]{3, 2, 1, 5, 6, 4};
//        System.out.println("题目3：" + medium.findKthLargest(nums3, 2));
//
//        int[] nums4 = new int[]{1, 2, 1, 3, 5, 6, 4};
//        System.out.println("题目4：" + medium.findPeakElement(nums4));

        int[] nums5 = new int[]{5, 7, 7, 8, 8, 10};
        System.out.println("题目5: " + Arrays.toString(medium.searchRange2(nums5, 8)));
    }

    /**
     * 题目2：前 K 个高频元素
     * Ref: https://blog.csdn.net/x273591655/article/details/84001236
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        // 统计元素的频次
        Map<Integer, Integer> int2FreqMap = new HashMap<>(16);
        for (int num : nums) {
            int2FreqMap.put(num, int2FreqMap.getOrDefault(num, 0) + 1);
        }

        // 桶排序
        List<Integer>[] bucket = new List[nums.length + 1];
        for (Integer key : int2FreqMap.keySet()) {
            int freq = int2FreqMap.get(key);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(key);
        }

        // 逆序（频次由高到低）取出元素
        List<Integer> ret = new ArrayList<>();
        for (int i = nums.length; i >= 0 && ret.size() < k; --i) {
            if (bucket[i] != null) {
                ret.addAll(bucket[i]);
            }
        }

        return ret;
    }

    /**
     * 题目3：数组中第K个最大元素
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 题目4：寻找峰值
     * 思路：题目要求时间复杂度O(logN),要想到二分法。
     * 注意题目条件：nums[i] ≠ nums[i+1]， 并且nums[-1] = nums[n] = -∞
     * 如果nums[i] < nums[i+1]，则峰值一定出现在i的右侧，否则左侧
     * refer: https://leetcode-cn.com/problems/find-peak-element/solution/hua-jie-suan-fa-162-xun-zhao-feng-zhi-by-guanpengc/
     */
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 面试题34：在排序数组中查找元素的第一个和最后一个位置
     *
     * @param nums   -- 升序数组
     * @param target -- 目标值
     *               <p>
     *               searchRange1 这种解法，超出了时间限制。因为最坏的时间复杂度有可能是O(n)
     *               searchRange2 分成两步，第一步是二分查找先找到第一个元素，第二步二分查找找到最后一个元素即可。时间复杂度O(logn)
     */
    public int[] searchRange2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};

        int firstPosition = findFirstPosition(nums, target);
        if (firstPosition == -1) {
            return new int[]{-1, -1};
        }
        int lastPosition = findLastPosition(nums, target);
        return new int[]{firstPosition, lastPosition};
    }

    private int findFirstPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                // 这里因为是要找到第一个元素，所以还要继续二分查找，比方这种case
                // 5,7,8,8,8,8,8,8,9。目的是继续查找
                right = mid;
            } else {
                right = mid - 1;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }

    private int findLastPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 这个地方也是区别
            int mid = (left + right + 1) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                // 这里因为是要找到第一个元素，所以还要继续二分查找，比方这种case
                // 5,7,8,8,8,8,8,8,9。目的是继续查找
                // 和第一个findFirstPosition 的区别。
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public int[] searchRange1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        if (nums.length == 1) {
            if (nums[0] == target) {
                return new int[]{0, 0};
            } else {
                return new int[]{-1, -1};
            }
        }
        int left = 0;
        int right = nums.length - 1;
        int mid = -1;
        boolean find = false;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                find = true;
                break;
            }
        }

        if (mid == -1 || find == false) return new int[]{-1, -1};
        int head = mid - 1;
        int tail = mid + 1;
        int headTemp = head;
        int tailTemp = tail;
        while (tailTemp++ < nums.length && nums[tail] == nums[mid]) {
            tail++;
        }

        while (headTemp-- > 0 && nums[head] == nums[mid]) {
            head--;
        }
        return new int[]{head + 1, tail - 1};
    }
}
