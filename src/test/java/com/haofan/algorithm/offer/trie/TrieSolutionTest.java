package com.haofan.algorithm.offer.trie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TrieSolutionTest {

    TrieSolution trie = new TrieSolution();

    @Test
    void insert() {
        Trie trie = new Trie();
        trie.insert("boy");
        trie.insert("baa");
        Assertions.assertTrue(trie.search("baa"));
        Assertions.assertTrue(trie.startsWith("ba"));
    }

    @Test
    void minValidStrings() {
        Assertions.assertEquals(3, trie.minValidStrings(new String[]{"abc", "aaaaa", "bcdef"}, "aabcdabc"));
    }
}