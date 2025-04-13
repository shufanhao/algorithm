package com.haofan.algorithm.offer.range;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RangeSolutionTest {

    RangeSolution offer = new RangeSolution();

    @Test
    void summaryRanges() {
        assertEquals(Arrays.asList("0->2", "4->5", "7"), offer.summaryRanges(new int[]{0,1,2,4,5,7}));
    }

    @Test
    void merge() {
        // intervals = [[1,3],[8,10],[2,6]，[15,18]]
        int[][] result = offer.merge(new int[][]{{1, 3}, {8, 10}, {2, 6}, {15, 18}});
        Assertions.assertEquals(3, result.length);
    }

    @Test
    void findMinArrowShots() {
        // Assertions.assertEquals(2, offer.findMinArrowShots(new int[][]{{10,16},{2,8},{1,6},{7,12}}));
        Assertions.assertEquals(4, offer.findMinArrowShots(new int[][]{{1,2},{2,3},{3,4},{4,5}}));
    }
}