package com.haofan.algorithm.offer.binary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySolutionTest {

    private BinarySolution binarySolution = new BinarySolution();

    @Test
    void addBinary() {
        assertEquals("10011", binarySolution.addBinary("1111", "0100"));
    }

    @Test
    void countBit() {
        assertArrayEquals(new int[]{0, 1, 1, 2, 1, 2}, binarySolution.countBits(5));
    }

    @Test
    void singleNumber() {
        assertEquals(100, binarySolution.singleNumberEasy(new int[]{0, 1, 0, 1, 100}));
        assertEquals(100, binarySolution.singleNumber(new int[]{0, 1, 0, 1, 0, 1, 100}));
    }

    @Test
    void maxProduct() {
        assertEquals(16, binarySolution.maxProduct(new String[]{"abcw", "baz", "foo", "bar", "fxyz", "abcdef"}));
    }
}