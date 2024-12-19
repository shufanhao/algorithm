package com.haofan.algorithm.offer.trie;


public class Trie {
    private TrieNode root = new TrieNode();

    // insert a string in a Trie Tree
    public void insert(String world) {
        TrieNode node = root;
        for (char ch : world.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new TrieNode();
            }
            node = node.children[ch - 'a'];
        }
        node.isWorld = true;
    }

    public boolean search(String world) {
        TrieNode node = root;

        for (char ch : world.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                return false;
            }
            node = node.children[ch - 'a'];
        }
        return node.isWorld;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;

        for (char ch : prefix.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                return false;
            }
            node = node.children[ch - 'a'];
        }

        return true;
    }

    // Trie data structure.
    public static class TrieNode {
        // 子节点可能有多个，子节点的index是对应的char, value是TrieNode
        TrieNode[] children;
        boolean isWorld;

        public TrieNode() {
            // 最多有26个小写字母
            children = new TrieNode[26];
        }
    }
}
