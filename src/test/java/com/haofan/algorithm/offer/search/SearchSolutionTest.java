package com.haofan.algorithm.offer.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchSolutionTest {
    SearchSolution search = new SearchSolution();
    @Test
    void searchInsert() {
        Assertions.assertEquals(2, search.searchInsert(new int[]{1, 3, 5, 6}, 5))   ;
    }

    @Test
    void peakIndexInMountainArray() {
        Assertions.assertEquals(2, search.peakIndexInMountainArray(new int[]{1,3,5,4,2}));
    }
}