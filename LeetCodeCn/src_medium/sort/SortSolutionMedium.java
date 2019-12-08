package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SortSolutionMedium {

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
     * @param nums
     * @param k
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public static void main(String args[]) {
        SortSolutionMedium medium = new SortSolutionMedium();
        int [] nums2 = new int[]{1, 1, 1, 2, 2, 3};
        System.out.println("题目2：" + medium.topKFrequent(nums2, 2));

        int [] nums3 = new int[]{3,2,1,5,6,4};
        System.out.println("题目3：" + medium.findKthLargest(nums3, 2));
    }
}
