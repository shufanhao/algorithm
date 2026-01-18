package com.haofan.algorithm.offer.trie;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordDictionaryTest {

    WordDictionary wordDictionary = new WordDictionary();

    @Test
    void addWord() {
        wordDictionary.addWord("addWord");
        // assertTrue(wordDictionary.search("add"));
    }

}