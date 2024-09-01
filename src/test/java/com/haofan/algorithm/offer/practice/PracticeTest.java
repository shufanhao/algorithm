package com.haofan.algorithm.offer.practice;

import com.haofan.algorithm.help.TreeNode;
import com.haofan.algorithm.offer.dp.DpSolution;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PracticeTest {

    private Practice dp = new Practice();

    @Test
    void minPathSum() {
        System.out.println(dp.minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
    }

    @Test
    void rob() {
        System.out.println(dp.rob(new int[]{1,2,3,1}));
    }

    @Test
    void rob3() {

    }
}