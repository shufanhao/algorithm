package com.haofan.algorithm.offer.binary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySolutionTest {

    private BinarySolution binarySolution = new BinarySolution();
    @Test
    void addBinary() {
        assertEquals("10011", binarySolution.addBinary("1111", "0100"));
    }

    @Test
    void countBit() {
        assertArrayEquals(new int[]{0, 1, 0}, binarySolution.countBits(2));
    }

    @Test
    void singleNumber() {
        assertEquals(100, binarySolution.singleNumber(new int[]{0, 1, 0, 1, 0, 1, 100}));
    }
}