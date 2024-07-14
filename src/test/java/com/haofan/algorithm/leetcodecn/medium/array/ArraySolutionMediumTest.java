package com.haofan.algorithm.leetcodecn.medium.array;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArraySolutionMediumTest {

    private ArraySolutionMedium solution = new ArraySolutionMedium();

    @Test
    public void nextPermutation() {
        int[] nums = new int[]{1,2,3,8,5,7,6,4};
        solution.nextPermutation(nums);

        assertArrayEquals(new int[]{1,2,3,8,6,4,5,7}, nums);
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
        Assertions.assertTrue(solution.canJump(new int[]{3,2,1,0,4}));
    }
}