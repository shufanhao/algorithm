import java.util.Arrays;

public class QuickSort {
    /**
     * 算法平均时间复杂度：N*logN, 最糟糕：N^2（正序或逆序）
    大致思路还是排序算法中比较简单的：
     http://wiki.jikexueyuan.com/project/easy-learn-algorithm/fast-sort.html
     1. 两个哨兵，分别从指向左边，右边。基准数是第一个元素
     2. 右边哨兵开始向左移动，如果发现比基准数小的，停下来。
     3. 左边哨兵开始向右移动，如果发现比基准数大的，停下来。
     4. 交换左右哨兵指向的元素，并继续向左，向右移动，直到左右哨兵碰头。
     5. 交换基准数和左右哨兵共同指向的元素。
     6. 基准数左边，右边分别按照上面的Step继续运算。
     */

    void quickSort(int nums[], int left, int right) {
        if (nums == null || nums.length == 0) {
            return;
        }
        if (left >= right) {
            return;
        }
        int i = left;
        int j = right;
        int privot = nums[i];
        // 犯错的地方：写成了while(i >= j)
        while (i != j) {
            while (j>i && nums[j] > privot) {
                j --;
            }
            while (j > i && nums[i] < privot) {
                i ++;
            }
            if (j > i) {
                // 交换两个元素
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        // 交换和基准位置的元素
        int temp = nums[i];
        nums[i] = privot;
        // 犯错的地方：写成了privot = temp
        nums[left] = temp;

        // 犯错的地方：写成了quickSort(nums, left, i);
        quickSort(nums, left, i-1);
        // 犯错的地方：写成了quickSort(nums, i, right)
        quickSort(nums, i+1, right);
    }
    public static void main(String args[]) {
        QuickSort obj = new QuickSort();
        int[] nums = {3,4,1,5,6};
        obj.quickSort(nums, 0, nums.length -1);
        System.out.println(Arrays.toString(nums));
    }
}