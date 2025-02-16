package com.haofan.algorithm.offer.hash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class HashSolutionTest {

    HashSolution offer = new HashSolution();

    @Test
    void randomizedSet() {
        HashSolution.RandomizedSet randomizedSet = new HashSolution.RandomizedSet();
        Assertions.assertTrue(randomizedSet.insert(1));
        Assertions.assertTrue(randomizedSet.insert(2));
        Assertions.assertTrue(randomizedSet.insert(3));
        Assertions.assertTrue(randomizedSet.remove(2));
        Assertions.assertFalse(randomizedSet.remove(2));
    }

    @Test
    void lruCache() {
        HashSolution.LRUCache obj = new HashSolution.LRUCache(10);
        obj.put(1, 1);
        obj.put(2, 2);
        obj.put(3, 3);
        Assertions.assertEquals(2, obj.get(2));
    }

    @Test
    void groupAnagrams() {
        String[] strs = new String[]{"eat", "tea", "nat", "tan", "bat", "ate"};
        System.out.println(offer.groupAnagrams(strs));
        Assertions.assertEquals(3, offer.groupAnagrams(strs).size());
    }

    @Test
    void isAlienSorted() {
        Assertions.assertTrue(offer.isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
    }

    @Test
    void findMinDifference() {
        String[] array = new String[]{"00:00", "23:59", "23:55"};
        Assertions.assertEquals(1, offer.findMinDifference1(Arrays.stream(array).toList()));
    }

    @Test
    void longestConsecutive() {
        Assertions.assertEquals(4, offer.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }

    @Test
    void canConstruct() {
        Assertions.assertTrue(offer.canConstruct("aa", "aab"));
    }

    @Test
    void isIsomorphic() {
        Assertions.assertFalse(offer.isIsomorphic("badc", "baba"));
    }

    @Test
    void wordPattern() {
        Assertions.assertTrue(offer.wordPattern("abba", "dog cat cat dog"));
    }

    @Test
    void isHappy() {
        Assertions.assertTrue(offer.isHappy(7));
    }
}