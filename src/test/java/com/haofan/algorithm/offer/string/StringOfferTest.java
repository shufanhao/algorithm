package com.haofan.algorithm.offer.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringOfferTest {

    private StringOffer offer = new StringOffer();
    @Test
    void checkInclusion() {
        assertTrue(offer.checkInclusion("ab", "eidbaooo"));
    }

    @Test
    void lengthOfLongestSubstring() {
        assertEquals(3, offer.lengthOfLongestSubstring("babcca"));
    }
}