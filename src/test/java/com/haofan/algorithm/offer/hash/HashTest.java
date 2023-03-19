package com.haofan.algorithm.offer.hash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class HashTest {

    Hash hash = new Hash();
    @Test
    void randomizedSet() {
        Hash.RandomizedSet randomizedSet = new Hash.RandomizedSet();
        Assertions.assertTrue(randomizedSet.insert(1));
        Assertions.assertTrue(randomizedSet.insert(2));
        Assertions.assertTrue(randomizedSet.insert(3));
        Assertions.assertTrue(randomizedSet.remove(2));
        Assertions.assertFalse(randomizedSet.remove(2));
    }

    @Test
    void groupAnagrams() {
        String[] strs = new String[] {"eat", "tea", "nat", "tan", "bat", "ate"};
        System.out.println(hash.groupAnagrams(strs));
        Assertions.assertEquals(3, hash.groupAnagrams(strs).size());
    }

    @Test
    void isAlienSorted() {
        Assertions.assertTrue(hash.isAlienSorted(new String[]{"hello","leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
    }

    @Test
    void findMinDifference() {
        String[] array = new String[] {"00:00","23:59","23:55"};
        Assertions.assertEquals(4, hash.findMinDifference(Arrays.stream(array).toList()));
    }
}