package com.haofan.algorithm.offer.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackSolutionTest {
    private StackSolution stack = new StackSolution();

    @Test
    void evalRPN() {
        Assertions.assertEquals(5, stack.evalRPN(new String[]{"2", "1", "3", "*", "+"}));
    }

    @Test
    void dailyTemperatures() {
        Assertions.assertArrayEquals(new int[]{1, 1, 1, 0}, stack.dailyTemperatures(new int[] {30,40,50,60}));
    }
}