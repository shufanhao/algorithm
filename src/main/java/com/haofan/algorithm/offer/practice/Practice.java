package com.haofan.algorithm.offer.practice;

import java.util.*;

import static com.haofan.algorithm.help.Common.swap;


public class Practice {

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // 初始化：
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }


    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];

        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int rob(int[] nums) {
        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int row = matrix.length;
        int column = matrix[0].length;
        int maxSide = 0;
        int[][] dp = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]);
                    }
                }
                maxSide = Math.max(maxSide, dp[i][j]);
            }
        }
        return maxSide * maxSide;
    }

    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();

        int max = Math.max(a.length(), b.length());
        int carry = 0;
        for (int i = 0; i < max; i++) {
            if (i < a.length()) {
                carry += getInt(a.charAt(a.length() - i - 1));
            }
            if (i < b.length()) {
                carry += getInt(b.charAt(b.length() - i - 1));
            }
            result.append(carry % 2);
            carry /= 2;
        }
        if (carry > 0) result.append('1');
        return result.reverse().toString();
    }

    private static int getInt(char c) {
        return c == '1' ? 1 : 0;
    }

    public int maxProduct(String[] words) {
        boolean[][] flags = new boolean[words.length][26];

        for (int i = 0; i < words.length; i++) {
            for (Character c : words[i].toCharArray()) {
                flags[i][c - 'a'] = true;
            }
        }

        int max = 0;
        // 遍历找到这样的两个单词
        for (int i = 0; i < words.length; i++) {
            for (int j = 1; j < words.length; j++) {
                int k = 0;
                for (; k < 26; k++) {
                    if (flags[i][k] && flags[j][k]) {
                        break;
                    }
                }
                if (k == 26) {
                    int prod = words[i].length() * words[j].length();
                    max = Math.max(prod, max);
                }
            }
        }
        return max;
    }

    public void sortColors(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                swap(nums, i, start++);
            } else if (nums[i] == 2) {
                swap(nums, i--, end--);
            }
        }
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] ans = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            ans[k] = list.get(k);
        }

        return ans;
    }

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >=0; i--) {
            if (digits[i] < 9) {
                digits[i] += 1;
                return digits;
            }
            digits[i] = 0;
        }
        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = 1;
        return newDigits;
    }

    public int[] moveZeroes(int[] nums) {
        int post = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[post++] = nums[i];
            }
        }

        for (int j = post; j < nums.length; j++) {
            nums[j] = 0;
        }

        return nums;
    }

    public void rotate(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        // 对调列元素
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len/2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][len - j -1];
                matrix[i][len - j -1] = temp;
            }
        }
    }

    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int right = 0;
        int minLen = 0;
        int sum = 0;
        while (left < nums.length) {
            if (right < nums.length && sum < target) {
                sum += nums[right++];
            } else {
                sum -= nums[left++];
            }

            if (sum >= target) {
                minLen = Math.min(minLen, right - left);
            }
        }

        if (minLen == nums.length + 1) {
            return 0;
        }

        return minLen;
    }

    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int maxSum = sum;
        for (int i = k; i < nums.length ; i++) {
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, sum);
        }

        return 1.0 * maxSum/k;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int p = 0;
        int q = nums.length - 1;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            p = i + 1;
            while (p < q) {
                if (nums[p] + nums[q] + nums[i] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[p]);
                    list.add(nums[q]);
                    ans.add(list);
                    p++;
                    q--;
                    // avoid duplicate
                    while ((p < q) && (nums[p - 1] == nums[p])) {
                        p++;
                    }
                } else if (nums[p] + nums[q] + nums[i] < 0) {
                    p++;
                    while ((p < q) && (nums[p - 1] == nums[p])) {
                        p++;
                    }
                } else {
                    // > 0
                    q--;
                }
            }
            // 这个地方要注意，为了防止重复，一定要加
            while ((i + 1) < nums.length && (nums[i] == nums[i + 1])) {
                i++;
            }
        }
        return ans;
    }

    public void setZeroes(int[][] matrix) {
        int[] rowsZero = new int[matrix.length];
        int[] columnZero = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rowsZero[0] = 1;
                    columnZero[j] = 1;
                }
            }
        }

        // 遍历每一行和每一个列
        for (int i = 0; i < rowsZero.length; i++) {
            if (rowsZero[i] == 1) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < columnZero.length; i++) {
            if (columnZero[i] == 1) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            char[] temp = strs[i].toCharArray();
            Arrays.sort(temp);
            String tempStr = String.valueOf(temp);
            if (map.containsKey(tempStr)) {
                map.get(tempStr).add(strs[i]);
            } else {
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(tempStr, list);
            }
        }

        return new ArrayList<>(map.values());
    }

    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int max = 0;
        while (l < r) {
            int gap = Math.min(height[l], height[r]);
            max = Math.max(max, gap * (r - l));
            if (height[l] == gap) {
                l++;
            } else {
                r --;
            }
        }
        return max;
    }

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
                // 关键点。
                cur = 0;
            }
        }

        return Math.max(cur, maxLength);
    }

    public int minSunArrayLen(int k, int[] nums) {
        int i = 0;
        int sum = 0;
        int min = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (i <= j && sum >= k) {
                min = Math.min(min, j - i + 1);
                sum -= nums[i++];
            }
        }
        return min;
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int i = 0;
        int count = 0;
        int product = 1;
        for (int j = i; j< nums.length; j++) {
            product *= nums[j];
            while (i <=j && product >= k) {
                product /= nums[i++];
            }
            count += j - i + 1;
        }
        return count;
    }

    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];

        left[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i -1] * nums[i -1];
        }
        right[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0 ; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = left[i] * right[i];
        }
        return ans;
    }

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }

        for (int i = 0; i < haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle)) {
                return i;
            }
        }

        return -1;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        Arrays.sort(strs);
        // 比较第一个和最后一个即可。
        int m = strs[0].length();
        int n = strs[strs.length - 1].length();
        int len = Math.min(m, n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (strs[0].charAt(i) == strs[strs.length - 1].charAt(i)) {
                sb.append(strs[0].charAt(i));
            } else {
                break;
            }
        }
        return sb.toString();
    }
}