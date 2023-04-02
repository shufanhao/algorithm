package com.haofan.algorithm.offer.trie;

// data structure of 前缀树
public class TrieNode {
    // 子节点可能有多个，子节点的index是对应的char, value是TrieNode
    TrieNode children[];
    boolean isWorld;

    public TrieNode() {
        // 最多有26个小写字母
        children = new TrieNode[26];
    }
}
