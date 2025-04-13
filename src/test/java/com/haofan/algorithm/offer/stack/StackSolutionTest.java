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
        // Assertions.assertEquals(5, stack.evalRPN(new String[]{"2", "1", "3", "*", "+"}));

       // Assertions.assertEquals(9, stack.evalRPN(new String[]{"2", "1", "+", "3", "*"}));

        Assertions.assertEquals(6, stack.evalRPN(new String[]{"4","13","5","/","+"}));

    }

    @Test
    void dailyTemperatures() {
        Assertions.assertArrayEquals(new int[]{1, 1, 1, 0}, stack.dailyTemperatures(new int[]{30, 40, 50, 60}));
    }

    @Test
    void simplifyPath() {
        Assertions.assertEquals("/.../b/d", stack.simplifyPath("/.../a/../b/c/../d/./"));
        Assertions.assertEquals("/", stack.simplifyPath("/../"));
        Assertions.assertEquals("/home/user/Pictures", stack.simplifyPath("/home/user/Documents/../Pictures"));
    }
}