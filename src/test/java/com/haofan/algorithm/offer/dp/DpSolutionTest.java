package com.haofan.algorithm.offer.dp;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DpSolutionTest {

    private DpSolution dp = new DpSolution();

    @Test
    void minCostClimbingStaris() {
        int res = dp.minCostClimbingStaris(new int[]{1, 100, 1, 1, 100, 1});
        assertEquals(4, res);
    }

    @Test
    void minCostClimbingStaris2() {
        int res = dp.minCostClimbingStaris2(new int[]{1, 100, 1, 1, 100, 1});
        assertEquals(4, res);
    }

    @Test
    void rob() {
        assertEquals(9, dp.rob(new int[]{2, 3, 4, 5, 3}));
    }

    @Test
    void robCycle() {
        assertEquals(8, dp.robCycle(new int[]{2, 3, 4, 5, 3}));
    }
}