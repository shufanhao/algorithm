package com.haofan.algorithm.offer.heap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class HeapSolution {
    /**
     * 面试题60：出现频率最高的K个数字
     * 给定一个整数数组 nums 和一个整数 k ，请返回其中出现频率前 k 高的元素。可以按 任意顺序 返回答案。
     * <p>
     * 解法：先用HashMap，key: 数组元素，value: 出现的次数.
     * <p>
     * 然后创建一个最小堆，遍历HashMap，去fill in 最小堆，如果min heap中已经有k个元素，则如果添加的数字>peak的频率，则删除栈顶元素，并添加
     * <p>
     * 时间复杂度O(klogk),
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numToCount = new HashMap<>();
        for (int num : nums) {
            numToCount.put(num, numToCount.getOrDefault(num, 0) + 1);
        }

        // 因为比较的是value, 所以要pass一个comparator,
        Queue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                (e1, e2) -> e1.getValue() - e2.getValue()
        );

        for (Map.Entry<Integer, Integer> entry : numToCount.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry);
            } else if (entry.getValue() > minHeap.peek().getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }

        List<Integer> result = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : minHeap) {
            result.add(entry.getKey());
        }

        int[] intArray = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            intArray[i] = result.get(i);
        }

        return intArray;
    }

    /**
     * 面试题59：
     * <a href="https://leetcode.cn/problems/kth-largest-element-in-a-stream/description/">...</a>
     * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
     * <p>
     * 请实现 KthLargest：
     * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
     * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素
     * <p>
     * 解法1：数据存储在排序数组中，只需要O(1)时间就可以知道第K大的数字，缺点:
     * 需要将读取的数据都放到排序数组中，需要O(n)空间复杂度, 并且排序数组中添加新的元素需要O(n)时间复杂度
     * 解法2：第K大元素，实际上是k个最大数字的最小值, 也就是将这K个元素放在最小堆中，然后求出栈顶元素就可以。
     */
    public static class KthLargest {
        // 优先队列的队头为队列中最小的元素，也就是第一个元素，也就是第 k 大的元素。
        private PriorityQueue<Integer> minHeap;
        private int size;

        public KthLargest(int k, int[] nums) {
            size = k;
            minHeap = new PriorityQueue<>();
            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            // 添加元素
            minHeap.offer(val);

            if (minHeap.size() > this.size) {
                // 删除栈顶元素。
                minHeap.poll();
            }
            // 返回的就是第k大的元素
            return minHeap.peek();
        }
    }
}
