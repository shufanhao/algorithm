package com.haofan.algorithm.leetcodecn.medium.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ArraySolutionMedium {
    private List<List<Integer>> ret = new LinkedList<>();
    private LinkedList<Integer> tempList = new LinkedList<>();

    /**
     * 题目2：矩阵置0
     * 这个没做出来, pass
     * <a href="https://leetcode.cn/problems/set-matrix-zeroes/description/">...</a>
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
     * 题目3：字母异味词分组, pass
     * <a href="https://leetcode.cn/problems/group-anagrams/">...</a>
     * <p>
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
     * failed: 没有想到是创建一个boolean[256]来统计
     * <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/">...</a>
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * 思路：
     * 并不是那么的容易想到这个解决思路，通过创建一个boolean[256] 来统计是否char出现过
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
     * failed: 忘记两次偶数和奇数两次了。
     *
     * <a href="https://leetcode.cn/problems/longest-palindromic-substring/description/">...</a>
     * https://juejin.im/entry/58c7c297da2f605dc5b3d139
     * 思路：回文子串是以中心轴对称的，可以从下表i出发，用左右两个指针，从i左右出发，判断是否相等，找出最大值即可
     * 注意：回文字符串有奇偶对称之分，如：abcba, abba，所以要判断两种类型
     */
    public String longestPalindrome(String s) {
        if (s == null) return null;

        String longest = s.substring(0, 1);
        String temp;
        for (int i = 0; i < s.length(); i++) {
            // 两次求，i, i,次，另外一次：i, i+1
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
     * failed: 没想出归来来。
     * <p>
     * <a href="https://leetcode.cn/problems/increasing-triplet-subsequence/">...</a>
     * <p>
     * <p>
     * 思路：有点像动态规划，维护一个二元组，first,second, 记录第i个元素之前的最小递增二元子序列
     */
    public boolean increasingTriplet(int[] nums) {
        if (nums == null) return false;
        if (nums.length < 3) return false;
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= first) {
                first = num;
            } else if (num <= second) {
                // num > first
                second = num;
            } else {
                // 说明num > first, > second
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

    private void backTracking(int[] nums, boolean[] numState) {
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
     * 面试题11: <a href="https://leetcode.cn/problems/container-with-most-water/description/">...</a>
     * <p>
     * 盛最多水的容器
     */
    public int maxArea(int[] height) {
        if (height == null) {
            return 0;
        }

        int l = 0;
        int r = height.length - 1;
        int maxWater = 0;
        while (l < r) {
            int min = Math.min(height[l], height[r]);
            maxWater = Math.max(min * (r - l), maxWater);
            if (height[l] == min) {
                l++;
            } else {
                r--;
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

        // 下面这段代码是为了防止不连续的括号，比方: )()()), 应该输出是2 而不是4，因为是不连续的括号。
        int cur = 0;
        int maxLength = 0;
        for (int num : temp) {
            if (num == 1) {
                cur++;
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
     * <p> 贪心算法
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

    // https://leetcode.cn/problems/jump-game-ii/?
    // 还是贪心算法，但是比较难， 解题：https://programmercarl.com/0045.跳跃游戏II.html#总结
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }
        //记录跳跃的次数
        int count=0;
        //当前的覆盖最大区域
        int curDistance = 0;
        //最大的覆盖区域
        int maxDistance = 0;
        for (int i = 0; i < nums.length; i++) {
            //在可覆盖区域内更新最大的覆盖区域
            maxDistance = Math.max(maxDistance,i+nums[i]);
            //说明当前一步，再跳一步就到达了末尾
            if (maxDistance>=nums.length-1){
                count++;
                break;
            }
            //走到当前覆盖的最大区域时，更新下一步可达的最大区域
            if (i==curDistance){
                curDistance = maxDistance;
                count++;
            }
        }
        return count;
    }
}


