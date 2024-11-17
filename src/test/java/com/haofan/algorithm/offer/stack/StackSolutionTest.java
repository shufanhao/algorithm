package com.haofan.algorithm.offer.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StackSolutionTest {
    private StackSolution stack = new StackSolution();

    @Test
    void findRightSmall() {
        Assertions.assertArrayEquals(new int[]{2, 2, -1, 4, -1}, stack.findRightSmall(new int[]{3, 4, 1, 5, 2}));
    }

    @Test
    void evalRPN() {
        Assertions.assertEquals(5, stack.evalRPN(new String[]{"2", "1", "3", "*", "+"}));
    }

    @Test
    void dailyTemperatures() {
        Assertions.assertArrayEquals(new int[]{1, 1, 1, 0}, stack.dailyTemperatures(new int[]{30, 40, 50, 60}));
    }
}