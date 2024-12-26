package com.haofan.algorithm.leetcodecn.medium.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArraySolutionMediumTest {

    private ArraySolutionMedium solution = new ArraySolutionMedium();

    @Test
    public void nextPermutation() {
        int[] nums = new int[]{1, 2, 3, 8, 5, 7, 6, 4};
        solution.nextPermutation(nums);

        assertArrayEquals(new int[]{1, 2, 3, 8, 6, 4, 5, 7}, nums);
    }

    @Test
    public void longestValidParentheses() {
        Assertions.assertEquals(4, solution.longestValidParentheses(")()())"));
    }

    @Test
    public void search1() {
        System.out.println(solution.search(new int[]{1, 2, 3, 4, 5}, 5));
    }

    @Test
    public void canJump() {
        Assertions.assertTrue(solution.canJump(new int[]{3, 2, 1, 0, 4}));
    }


    @Test
    void setZeroes() {
        int nums_2[][] = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}};
        solution.setZeroes(nums_2);
        System.out.println("题目2：");
        for (int i = 0; i < nums_2.length; i++) {
            for (int j = 0; j < nums_2[i].length; j++) {
                System.out.print(nums_2[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Test
    void groupAnagrams() {
        String[] strsIssue3 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> listsIssue3 = solution.groupAnagrams(strsIssue3);
        System.out.println(Arrays.toString(listsIssue3.toArray()));
    }

    @Test
    void lengthOfLongestSubstring() {
        String strsIssue4 = "pwwkew";
        System.out.println("题目4：" + solution.lengthOfLongestSubstring(strsIssue4));
    }

    @Test
    void longestPalindrome() {
        String strsIssue5 = "babad";
        System.out.println("题目5：" + solution.longestPalindrome(strsIssue5));
    }

    @Test
    void increasingTriplet() {
        // int numsIssue6[] = {0, 4, 2, 1, 0, -1, -3};
        int numsIssue6[] = {2, 1, 5, 0, 4, 6};
        System.out.println("题目6：" + solution.increasingTriplet(numsIssue6));
    }

    @Test
    void permute() {
        int nums[] = {1, 2, 3};
        System.out.println("题目7: ");
        solution.permute(nums);
    }

    @Test
    void maxArea() {
        int height[] = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("题目11：" + solution.maxArea(height));
    }

    @Test
    void testNextPermutation() {
    }

    @Test
    void testLongestValidParentheses() {
    }

    @Test
    void search() {
    }

    @Test
    void testCanJump() {
    }
}