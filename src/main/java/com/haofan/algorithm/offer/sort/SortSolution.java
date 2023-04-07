package com.haofan.algorithm.offer.sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SortSolution {
    /**
     * 面试题1：合并区间
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     *
     * 输入：intervals = [[1,3],[8,10],[2,6]，[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     *
     * 思路：
     * 1. 先按照第一个元素排序。排序后是[[1,3],[2,6],[8,10],[15,18]]
     * 2. 再比较相邻的，后面的start是否小于前一个的end，如果是就可以继续比较。[1,3],[2,6] 合并成[1, 6]
     * 3. 继续比较[1,6] 和 [8, 10]，后面的start大于前一个的end，所以就选择出[1,6] 和 [8, 10]
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);

        List<int[]> merged = new LinkedList<>();

        int i = 0;
        while ( i < intervals.length) {
            // 第一个元素
            int[] temp = new int[]{intervals[i][0], intervals[i][1]};
            int j = i + 1;
            while ( j < intervals.length && intervals[j][0] <= temp[1]) {
                temp[1] = Math.max(temp[1], intervals[j][1]);
                j ++;
            }
            merged.add(temp);
            i = j;
        }

        int[][] result = new int[merged.size()][];
        return merged.toArray(result);
    }

    /**
     * 面试题2：计数排序
     *
     * 解法：先统计每个数组中每个整数在数组中出现的次数，然后按照从小到大顺序填入数组中。
     *  如: [2, 3, 2, 3, 2, 1]，1出现了1次，2出现3次，3出现2次。，所以依次在数组中填入1个1，,3个2，,2个3。
     */
    public int[] sortArray(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // find the min and max from array
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int[] counts = new int[max - min + 1];
        for (int num : nums) {
            counts[num - min] ++;
        }

        int i = 0;
        // fill in num
        for (int num = min; num <= max; num ++) {
            while (counts[num - min] > 0) {
                nums[i++] = num;
                counts[num - min] --;
            }
        }

        return nums;
    }

    /**
     * 面试题3：数组相对排序(<a href="https://leetcode.cn/problems/relative-sort-array/">link</a>)
     * <p>
     * 两个数组，arr1 和arr2，arr2中的元素各不相同，arr2 中的每个元素都出现在arr1中。
     * <p>
     * 对 arr1中的元素进行排序，使 arr1 中项的相对顺序和arr2中的相对顺序相同。未在arr2中出现过的元素需要按照升序放在arr1的末尾。
     * 假设数组中的元素都是0-1000范围内
     *
     * 思路：计数排序。
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] counts = new int[1001];
        for (int num : arr1) {
            counts[num] ++;
        }

        int i = 0;
        for (int num: arr2) {
            while (counts[num] > 0) {
                arr1[i++] = num;
                counts[num] -- ;
            }
        }

        // arr2中出现过的元素需要按照升序放在arr1的末尾。
        for (int num = 0; num < counts.length; num ++) {
            while (counts[num] > 0) {
                arr1[i++] = num;
                counts[num] --;
            }
        }
        return arr1;
    }
}
