package com.haofan.algorithm.offer.binary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTest {

    private Binary binary = new Binary();
    @Test
    void addBinary() {
        assertEquals("10011", binary.addBinary("1111", "0100"));
    }

    @Test
    void countBit() {
        assertArrayEquals(new int[]{0, 1, 0}, binary.countBits(2));
    }

    @Test
    void singleNumber() {
        assertEquals(100, binary.singleNumber(new int[]{0, 1, 0, 1, 0, 1, 100}));
    }
}