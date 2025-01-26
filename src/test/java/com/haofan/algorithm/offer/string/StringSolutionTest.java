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
    void findAnagrams() {
        assertArrayEquals(new Object[]{0, 6}, offer.findAnagrams("cbaebabacd", "abc").toArray());
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
    void validPalindrome() {
        assertTrue(offer.validPalindrome("abca"));
    }

    @Test
    void replaceSpace() {
        assertEquals("We%20are%20happy", offer.replaceSpace("We are happy"));
    }

    @Test
    void decodeString() {
        assertEquals("accaccacc", offer.decodeString("3[a2[c]]"));
    }

    @Test
    void isSubstringPresent() {
        assertTrue(offer.isSubstringPresent("leetcode"));
        assertFalse(offer.isSubstringPresent("abcd"));
    }

    @Test
    void romanToInt() {
        assertEquals(1994, offer.romanToInt("MCMXCIV"));
    }

    @Test
    void intToRoman() {
        assertEquals("MCMXCIV", offer.intToRoman(1994));
    }
}