package com.haofan.algorithm.offer.dp;

import com.haofan.algorithm.help.TreeNode;
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

    @Test
    void uniquePaths() {
        assertEquals(28, dp.uniquePaths(7, 3));
    }

    @Test
    void minPathSum() {
        assertEquals(7, dp.minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
    }

    @Test
    void climbStairs() {
        assertEquals(8, dp.climbStairs(5));
    }

    @Test
    void maximalSquare() {
        char[][] matrix = new char[][]{{'0','1'},{'1','0'}};
        assertEquals(1, dp.maximalSquare(matrix));
    }

    @Test
    void coinChange() {
        assertEquals(4, dp.change(5, new int[]{1, 2, 5}));
    }

    @Test
    void numSquares() {
        assertEquals(3, dp.numSquares(12));
    }

    @Test
    void lengthOfLIS() {
        assertEquals(4, dp.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }

    @Test
    void rob3() {
        assertEquals(32, dp.rob3(TreeNode.buildTreeNode()));
    }
}