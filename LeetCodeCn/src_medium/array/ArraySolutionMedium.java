package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraySolutionMedium {

    /**
     * 三数之和：给定一个包含n个整数的数组nums, 判断nums中是否存在三个元素a,b,c，使得
     * a + b + c = 0, 并找出满足条件且不重复的三元组
     * https://blog.csdn.net/MebiuW/article/details/50918450
     * 思路：先排序，然后选第一个为基准位置，然后在这个值后面i+1,和末尾n-1 分别初始化两个指针 p, q；判断p,q位置的值是否等于target，如果
     * 等于就输出，并且(p++,q--)，如果小于则p++, 如果大于则q--,上述过程一直遇到p==q, 又回到i,选择下一个基准位置
     * 特别注意：
     * 1. 注意i,p,q 取值范围
     * 2. 为了防止重复，在移动指针的时候，如果遇到移动后和移动前的指针位置的值一样，则要做一个移动，知道和上一个取值不一样
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length <=2) {
            return result;
        }
        Arrays.sort(nums);
        int i, p, q, reverse;
        for (i=0; i<nums.length - 2; i++) {
            reverse = -nums[i];
            p = i+1;
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
                    while ((p<q) && (nums[p-1] == nums[p])) {
                        p++;
                    }
                    q--;
                } else if ((nums[p] + nums[q]) < reverse) {
                    p++;
                    // 这个地方要注意，为了防止重复，一定要加
                    while ((p<q) && (nums[p-1] == nums[p])) {
                        p++;
                    }
                } else {
                    q--;
                }
            }
            // 这个地方要注意，为了防止重复，一定要加
            while ( (i+1) < nums.length && (nums[i] == nums[i+1])) {
                i++;
            }
        }
        return result;
    }

    public static void main(String args[]) {
        ArraySolutionMedium array = new ArraySolutionMedium();
        int nums_1[] = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> listList = array.threeSum(nums_1);
        System.out.println(Arrays.toString(listList.toArray()));
    }
}


