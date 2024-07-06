package com.haofan.algorithm.offer.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchSolutionTest {
    SearchSolution search = new SearchSolution();

    @Test
    void search() {
        Assertions.assertEquals(1, search.search(new int[]{1, 2, 3, 4, 5}, 2));
    }
    @Test
    void searchInsert() {
        Assertions.assertEquals(2, search.searchInsert(new int[]{1, 3, 5, 6}, 5))   ;
    }

    @Test
    void peakIndexInMountainArray() {
        Assertions.assertEquals(2, search.peakIndexInMountainArray(new int[]{1,3,5,4,2}));
    }

    @Test
    void singleNonDuplicate() {
        Assertions.assertEquals(3, search.singleNonDuplicate(new int[]{1,1,2,2,3,4,4,5,5}));
    }
}