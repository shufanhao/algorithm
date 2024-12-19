package com.haofan.algorithm.offer.trie;

import java.util.Arrays;

public class TrieSolution {
    // https://leetcode.cn/problems/minimum-number-of-valid-strings-to-form-target-i/description/
    // Input: words = ["abababab","ab"], target = "ababaababa"
    public int minValidStrings(String[] words, String target) {
        if (words == null || words.length == 0 || target == null || target.length() == 0) {
            return -1;
        }

        // Create a Trie and insert all words into the Trie
        Trie trie = new Trie();
        Arrays.stream(words).forEach(trie::insert);

        // dp array to store the minimum number of valid strings needed to form the target
        int[] dp = new int[target.length() + 1];
        Arrays.fill(dp, Integer.MAX_VALUE); // Initialize dp array to a large number
        // dp[0] 表示不需要任何字符串都能构建一个空的目标字符串
        // 假设目标字符串为 target = "abc"，那么：
	    // dp[0] 表示构建空字符串的最小字符串数量，这个值为 0。
	    // dp[1] 表示构建前 1 个字符（"a"）的最小字符串数量。
	    // dp[2] 表示构建前 2 个字符（"ab"）的最小字符串数量。
	    // dp[3] 表示构建完整目标字符串（"abc"）的最小字符串数量。

        dp[0] = 0; // No strings needed to form an empty string

        // Iterate through all possible prefixes of the target string
        for (int i = 1; i <= target.length(); i++) {
            for (int j = 0; j < i; j++) {
                String subString = target.substring(j, i);
                if (dp[j] != Integer.MAX_VALUE && trie.startsWith(subString)) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        // If dp[target.length()] is still Integer.MAX_VALUE, return -1 (impossible case)
        return dp[target.length()] == Integer.MAX_VALUE ? -1 : dp[target.length()];
    }
}
