package com.haofan.algorithm.offer.range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RangeSolution {

    /**
     * Leetcode 228. Summary Ranges
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();

        int i = 0;
        int j = 0;
        while (i < nums.length) {
            j = i + 1;
            while ((j < nums.length) && (nums[j - 1] + 1) == nums[j]) {
                j++;
            }
            // j = 5
            if ((i + 1) == j) {
                ans.add(String.valueOf(nums[i]));
            } else {
                ans.add(nums[i] + "->" + nums[j - 1]);
            }

            // i -> 5
            // j -> 6
            i = j;
        }
        return ans;
    }

    /**
     * Leetcode 56. Merge Intervals
     *
     * <a href="https://leetcode.cn/problems/merge-intervals/description/">...</a>
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     * <p>
     * 输入：intervals = [
     * [1,3],
     * [8,10],
     * [2,6]，
     * [15,18]
     * ]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * <p>
     * 思路：
     * 1. 先按照第一个元素排序。排序后是[[1,3],[2,6],[8,10],[15,18]]
     * 2. 再比较相邻的，后面的start是否小于前一个的end，如果是就可以继续比较。[1,3],[2,6] 合并成[1, 6]
     * 3. 继续比较[1,6] 和 [8, 10]，后面的start大于前一个的end，所以就选择出[1,6] 和 [8, 10]
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);

        List<int[]> merged = new LinkedList<>();

        int i = 0;
        while (i < intervals.length) {
            // 第一个元素， int[] temp = [1, 3]
            int[] temp = new int[]{intervals[i][0], intervals[i][1]};
            int j = i + 1;
            while (j < intervals.length && intervals[j][0] <= temp[1]) {
                temp[1] = Math.max(temp[1], intervals[j][1]);
                j++;
            }
            merged.add(temp);
            i = j;
        }

        int[][] result = new int[merged.size()][];
        return merged.toArray(result);
    }

    /**
     * 57. Insert Interval <a href="https://leetcode.cn/problems/insert-interval">...</a>
     * <p>
     * 把newInterval添加到intervals中， 然后调56题的方法
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] newIntervals = new int[intervals.length + 1][];
        for (int i = 0; i < intervals.length; i++) {
            newIntervals[i] = intervals[i].clone();
        }
        newIntervals[intervals.length] = newInterval.clone();
        return merge(newIntervals);
    }

    /**
     * Leetcode 452: <a href="https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons">...</a>
     * 题目不是很容易理解， 不正确。
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (point0, point1) -> (point0[0] - point1[1]));
        // [1,6], [2,8], [7,12], [10,16]
        int i = 0;
        int count = 0;
        while (i < points.length) {
            // temp: [1, 6]
            int[] temp = new int[]{points[i][0], points[i][1]};
            int j = i + 1;
            // 2 < 6
            while (j < points.length && points[j][0] <= temp[1]) {
                temp[1] = Math.max(temp[1], points[j][1]);
                j++;
            }
            count++;
            i = j;
        }
        return count;
    }
}
