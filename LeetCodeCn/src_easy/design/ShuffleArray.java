package design;

import java.util.Random;

/**
 * 题目1： 打乱一个没有重复元素的数组
 */
public class ShuffleArray {
    private int[] numsOrin;

    public ShuffleArray(int[] nums) {
        this.numsOrin = nums;
    }

    public static void main(String[] args) {
        int nums[] = {1, 2, 3, 4};
        ShuffleArray s = new ShuffleArray(nums);
        int shuffle[] = s.shuffle();
        for (int i = 0; i < shuffle.length; i++) {
            System.out.println(shuffle[i]);
        }

    }

    public int[] reset() {
        return this.numsOrin;
    }

    // return a random shuffling of the array
    public int[] shuffle() {
        if (numsOrin == null || numsOrin.length == 0) {
            return numsOrin;
        }
        int[] cur = new int[numsOrin.length];
        for (int i = 0; i < numsOrin.length; i++) {
            cur[i] = numsOrin[i];
        }
        // shuffle, 就是用random随机取出一个数，然后这个数，的对应的数组元素和数组的最后一个元素交换
        // 其实就是随机交换位置。
        Random random = new Random();
        for (int i = cur.length - 1; i >= 0; i--) {
            int pos = random.nextInt(i + 1);
            int temp = cur[pos];
            cur[pos] = cur[i];
            cur[i] = temp;
        }
        return cur;
    }
}
