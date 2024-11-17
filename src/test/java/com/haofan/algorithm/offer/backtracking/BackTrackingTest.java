package com.haofan.algorithm.offer.backtracking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BackTrackingTest {

    BackTracking tracking = new BackTracking();

    @Test
    void combine() {
        assertEquals(6, tracking.combine(4, 2).size());
    }
}