package com.haofan.algorithm.offer.heap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HeapSolutionTest {

    private HeapSolution.KthLargest kthLargest = new HeapSolution.KthLargest(2, new int[]{4, 5, 8, 2});
    private HeapSolution heap = new HeapSolution();
    @Test
    void kthLargest() {
        Assertions.assertEquals(8, kthLargest.add(10));
    }

    @Test
    void topKFrequent() {
        Assertions.assertEquals(List.of(2, 1), heap.topKFrequent(new int[]{1, 2, 2, 1, 3, 1}, 2));
    }
}