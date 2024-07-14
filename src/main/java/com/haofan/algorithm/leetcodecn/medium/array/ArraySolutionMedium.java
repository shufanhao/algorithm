package com.haofan.algorithm.leetcodecn.medium.array;

import java.util.*;
import java.util.function.BinaryOperator;

import static com.haofan.algorithm.help.Common.swap;

public class ArraySolutionMedium {
    private List<List<Integer>> ret = new LinkedList<>();
    private LinkedList<Integer> tempList = new LinkedList<>();

    public static void main(String args[]) {
        ArraySolutionMedium array = new ArraySolutionMedium();
        int nums_1[] = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> listList = array.threeSum(nums_1);
        System.out.println("题目1：" + Arrays.toString(listList.toArray()));

        int nums_2[][] = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}};
        array.setZeroes(nums_2);
        System.out.println("题目2：");
        for (int i = 0; i < nums_2.length; i++) {
            for (int j = 0; j < nums_2[i].length; j++) {
                System.out.print(nums_2[i][j] + " ");
            }
            System.out.println();
        }

        String[] strsIssue3 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> listsIssue3 = array.groupAnagrams(strsIssue3);
        System.out.println(Arrays.toString(listsIssue3.toArray()));

        String strsIssue4 = "pwwkew";
        System.out.println("题目4：" + array.lengthOfLongestSubstring(strsIssue4));

        String strsIssue5 = "babad";
        System.out.println("题目5：" + array.longestPalindrome(strsIssue5));

        int numsIssue6[] = {0, 4, 2, 1, 0, -1, -3};
        System.out.println("题目6：" + array.increasingTriplet(numsIssue6));

        int nums[] = {1, 2, 3};
        System.out.println("题目7: ");
        array.permute(nums);

        int height[] = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("题目11：" + array.maxArea(height));

    }

    /**
     * 三数之和：给定一个包含n个整数的数组nums, 判断nums中是否存在三个元素a,b,c，使得
     * a + b + c = 0, 并找出满足条件且不重复的三元组
     * https://blog.csdn.net/MebiuW/article/details/50918450
     * 思路：先排序，然后选第一个为基准位置，然后在这个值后面i+1,和末尾n-1 分别初始化两个指针 p, q；判断p,q位置的值是否等于target，如果
     * 等于就输出，并且(p++,q--)，如果小于则p++, 如果大于则q--,上述过程一直遇到p==q, 又回到i,选择下一个基准位置
     * 特别注意：
     * 1. 注意i,p,q 取值范围
     * 2. 为了防止重复，在移动指针的时候，如果遇到移动后和移动前的指针位置的值一样，则要做一个移动，知道和上一个取值不一样
     *
     * @param nums
     * @return
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

    /**
     * 题目2：矩阵置0
     * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法
     * 1. O(m*n)解法：原数据x[m][n]，复制一份数据到y[m][n]，遍历y，根据其中的元素是否是0，对x中元素置0。
     * 2. O(m+n)空间的解法, X[m]来记录某一列是否需要置零，用Y[n]来记录某一行是否需要置零，遍历原始数据，如果
     * 某一元素为0，则修改x[m], Y[n],最后遍历X,Y来标记对应的行和列.
     * 下面function用思路2，进行求解
     */
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int[] m = new int[matrix[0].length]; //记录某一列是否需要置0
        int[] n = new int[matrix.length]; // 记录某一行是否需要置0
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    m[j] = 1;//认为是0
                    n[i] = 1;//认为是0
                }
            }
        }
        // 遍历m，如果发现元素时1，则将该列所有元素置0
        for (int i = 0; i < m.length; i++) {
            if (m[i] == 1) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        // 遍历n，如果发现元素时1，则将该行所有元素置0
        for (int i = 0; i < n.length; i++) {
            if (n[i] == 1) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 题目3：字母异味词分组
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * 思路：将字符串转成字符数组，并排序，然后放入key中，判断map中是否要该key
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] strChar = strs[i].toCharArray();
            Arrays.sort(strChar);
            String temp = String.valueOf(strChar);
            if (map.containsKey(temp)) {
                map.get(temp).add(strs[i]);
            } else {
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(temp, list);
            }
        }
        return new ArrayList<List<String>>(map.values());
    }

    /**
     * 题目4：无重复字符的最长子串
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 思路：
     * 从start开始遍历，但是第一步是while loop 来推进end, 直到推不动end, 然后start++
     * 巧妙：为end是外围variable，在start的loop上，end不会重置；[start~end]中间不需要重复计算
     * 最终可以O（n）
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        boolean[] chars = new boolean[256]; // 256 ASCII code
        int longest = 0;
        int left = 0;
        int right = 0;
        while (left < s.length()) {
            while (right < s.length() && !chars[s.charAt(right)]) {
                chars[s.charAt(right)] = true;
                longest = Math.max(longest, right - left + 1);
                right++;
            }
            chars[s.charAt(left)] = false; // 这个地方是关键
            left++;
        }
        return longest;
    }

    /**
     * 题目5: 最长回文子串
     * https://juejin.im/entry/58c7c297da2f605dc5b3d139
     * 思路：回文子串是以中心轴对称的，可以从下表i出发，用左右两个指针，从i左右出发，判断是否相等，找出最大值即可
     * 注意：回文字符串有奇偶对称之分，如：abcba, abba，所以要判断两种类型
     */
    public String longestPalindrome(String s) {
        if (s == null) return null;

        String longest = s.substring(0, 1);
        String temp;
        for (int i = 0; i < s.length(); i++) {
            // 两次求，i, i一次，另外一次：i, i+1
            temp = findPalindrome(s, i, i);
            longest = temp.length() > longest.length() ? temp : longest;
            temp = findPalindrome(s, i, i + 1);
            longest = temp.length() > longest.length() ? temp : longest;
        }
        return longest;
    }

    public String findPalindrome(String str, int left, int right) {
        int len = str.length();
        while (left >= 0 && right <= len - 1 && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return str.substring(left + 1, right);
    }

    /**
     * 递增的三元子序列：给定一个未排序的数组，判断数组是否存在长度为3的递增子序列
     * 思路：有点像动态规划，维护一个二元组，first,second, 记录第i个元素之前的最小递增二元子序列
     */
    public boolean increasingTriplet(int[] nums) {
        if (nums == null) return false;
        if (nums.length < 3) return false;
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int num : nums) {
            // 不太清楚为什么是这样写，感觉不是很合理
            if (num <= first) {
                first = num;
            } else if (num <= second) {
                second = num;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 全排列, 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * <p>
     * 以{1,2,3}为例，它的排列是：
     * 以1开头，后面接着{2,3}的全排列，
     * 以2开头，后面接着{1,3}的全排列，
     * 以3开头，后面接着{1,2}的全排列。
     * <p>
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */
    public List<List<Integer>> permute(int[] nums) {
        // 定义一个与 nums 等长的数组，用于记录当前的值是否被使用
        // true 表示已被使用
        // false 表示未被使用
        boolean[] numState = new boolean[nums.length];
        backTracking(nums, numState);
        System.out.println(Arrays.toString(ret.toArray()));
        return ret;
    }

    public void backTracking(int[] nums, boolean[] numState) {
        // tempList 和 nums 长度相等表明所有元素都已经添加
        if (tempList.size() == nums.length) {
            ret.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 如果元素已经被使用过，则直接进入下一轮循环
            if (numState[i]) {
                continue;
            }
            tempList.add(nums[i]);
            numState[i] = true;

            backTracking(nums, numState);

            numState[i] = false;
            tempList.removeLast();
        }
    }

    /**
     * 面试题11: https://leetcode.cn/problems/container-with-most-water/description/
     * <p>
     * 盛最多水的容器
     */
    public int maxArea(int[] height) {
        if (height == null) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;
        while (left < right) {
            int min = Math.min(height[left], height[right]);
            maxWater = Math.max(min * (right - left), maxWater);
            if (height[left] == min) {
                left++;
            } else {
                right--;
            }
        }
        return maxWater;
    }

    /**
     * 面试题31： 下一个排列
     * <a href="https://leetcode.cn/problems/next-permutation/solutions/80560/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/">...</a>
     * 解法，找规律
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int i = nums.length - 2;
        int j = nums.length - 1;

        // 从右向左找到第一个满足递增的元素对 (nums[i] < nums[j])
        while (i >= 0 && nums[i] >= nums[j]) {
            i--;
            j--;
        }

        if (i > 0) {
            // 找到了
            int k = nums.length - 1;
            // 从右向左找到第一个大于 nums[i] 的元素 nums[k]
            while (nums[i] >= nums[k]) {
                k--;
            }

            // 交换 nums[i] 和 nums[k]
            int temp = nums[i];
            nums[i] = nums[k];
            nums[k] = temp;
        }

        // 反转从 j 开始的剩余部分
        int left = j;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 面试题32. 最长有效括号
     * <a href="https://leetcode.cn/problems/longest-valid-parentheses/description/">...</a>
     * <p>
     * 容易理解：<a href="https://leetcode.cn/problems/longest-valid-parentheses/solutions/2719468/chao-jian-dan-fang-fa-zhi-hui-gua-hao-pi-nbby/">...</a>
     */
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 1) {
            return 0;
        }

        int[] temp = new int[s.length()];

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty()) {
                    int j = stack.pop();
                    if (s.charAt(j) == '(') {
                        temp[i] = 1;
                        temp[j] = 1; // 匹配成功
                    }
                }
            }
        }

        int cur = 0;
        int maxLength = 0;
        for (int num : temp) {
            if (num == 1) {
                cur ++;
            } else {
                maxLength = Math.max(cur, maxLength);
                cur = 0;
            }
        }

        return Math.max(cur, maxLength);
    }


    /**
     * 面试题33：搜索旋转排序数组
     * <a href="https://leetcode.cn/problems/search-in-rotated-sorted-array/description/">...</a>
     * 思路：二分法查找
     */

    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 面试题55: 跳跃游戏
     * <a href="https://leetcode.cn/problems/jump-game/description/">...</a>
     * <p>
     * 思路：看下最远能到的位置是否大于等于数组中的最后一个位置，即可。
     */
    public boolean canJump(int[] nums) {
        int rightMost = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i <= rightMost) { // 这个限定条件一定要写。
                rightMost = Math.max(rightMost, i + nums[i]);
                if (rightMost >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}


