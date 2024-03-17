package com.haofan.algorithm.offer.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringSolutionTest {

    private StringSolution offer = new StringSolution();
    @Test
    void checkInclusion() {
        assertTrue(offer.checkInclusion("ab", "eidbaooo"));
    }

    @Test
    void lengthOfLongestSubstring() {
        assertEquals(3, offer.lengthOfLongestSubstring("babcca"));
    }

    @Test
    void minWindow() {
        assertEquals("BANC", offer.minWindow("ADDBANCAD", "ABC"));
    }

    @Test
    void isPalindrome() {
        assertTrue(offer.isPalindrome("noon!"));
        assertFalse(offer.isPalindrome("haofan"));
    }

    @Test
    void replaceSpace() {
        assertEquals("We%20are%20happy", offer.replaceSpace("We are happy"));
    }
}