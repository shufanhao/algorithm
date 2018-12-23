package array;

import java.util.*;

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
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j< matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    m[j] = 1;//认为是0
                    n[i] = 1;//认为是0
                }
            }
        }
        // 遍历m，如果发现元素时1，则将该列所有元素置0
        for (int i=0; i<m.length; i++) {
            if (m[i] == 1) {
                for (int j=0; j<matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        // 遍历n，如果发现元素时1，则将该行所有元素置0
        for (int i=0; i<n.length; i++) {
            if (n[i] == 1) {
                for (int j=0; j<matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 题目3：字谜数组
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * 思路：将字符串转成字符数组，并排序，然后放入key中，判断map中是否要该key
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length ==0 ){
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (int i=0; i<strs.length; i++) {
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
        if (s == null || s.length() == 0) {
            return 0;
        }
        boolean[] chars = new boolean[256]; // 256 ASCII code
        int rst = 0;
        int start = 0;
        int end = 0;
        while (start<s.length()) {
            while (end < s.length() && !chars[s.charAt(end)]) {
                chars[s.charAt(end)] = true;
                rst = Math.max(rst, end - start + 1);
                end ++;
            }
            chars[s.charAt(start)] = false; // 这个地方是关键
            start++;
        }
        return rst;
    }

    /**
     * 题目5: 最长回文子串
     * https://juejin.im/entry/58c7c297da2f605dc5b3d139
     * 思路：回文子串是以中心轴对称的，可以从下表i出发，用左右两个指针，从i左右出发，判断是否相等，找出最大值即可
     * 注意：回文字符串有奇偶对称之分，如：abcba, abba，所以要判断两种类型
     */
    public String longestPalindrome(String s) {
        if (s == null) return null;
        if (s.length() == 0) return "";
        String longest = s.substring(0, 1);
        String temp;
        for (int i=0; i<s.length(); i++) {
            // 两次求，i, i一次，另外一次：i, i+1
            temp = findPalindrome(s, i, i);
            longest = temp.length() > longest.length() ? temp : longest;
            temp = findPalindrome(s, i, i+1);
            longest = temp.length() > longest.length() ? temp : longest;
        }
        return longest;
    }

    public String findPalindrome(String str, int left, int right) {
        int len = str.length();
        while (left >=0 && right <= len -1 && str.charAt(left) == str.charAt(right)) {
            left --;
            right ++;
        }
        return str.substring(left +1 , right);
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

    public static void main(String args[]) {
        ArraySolutionMedium array = new ArraySolutionMedium();
        int nums_1[] = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> listList = array.threeSum(nums_1);
        System.out.println("题目1：" + Arrays.toString(listList.toArray()));

        int nums_2[][] = {
                {0,1,2,0},
                {3,4,5,2},
                {1,3,1,5}};
        array.setZeroes(nums_2);
        System.out.println("题目2：");
        for (int i=0; i<nums_2.length; i++) {
            for (int j=0; j< nums_2[i].length;j++) {
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

        int numsIssue6[] = {0,4,2,1,0,-1,-3};
        System.out.println("题目6：" + array.increasingTriplet(numsIssue6));
    }
}


