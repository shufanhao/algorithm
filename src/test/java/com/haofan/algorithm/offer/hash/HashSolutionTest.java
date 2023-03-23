package com.haofan.algorithm.offer.hash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class HashSolutionTest {

    HashSolution hashSolution = new HashSolution();
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
    void groupAnagrams() {
        String[] strs = new String[] {"eat", "tea", "nat", "tan", "bat", "ate"};
        System.out.println(hashSolution.groupAnagrams(strs));
        Assertions.assertEquals(3, hashSolution.groupAnagrams(strs).size());
    }

    @Test
    void isAlienSorted() {
        Assertions.assertTrue(hashSolution.isAlienSorted(new String[]{"hello","leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
    }

    @Test
    void findMinDifference() {
        String[] array = new String[] {"00:00","23:59","23:55"};
        Assertions.assertEquals(4, hashSolution.findMinDifference(Arrays.stream(array).toList()));
    }
}