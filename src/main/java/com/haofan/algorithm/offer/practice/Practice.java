package com.haofan.algorithm.offer.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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


}