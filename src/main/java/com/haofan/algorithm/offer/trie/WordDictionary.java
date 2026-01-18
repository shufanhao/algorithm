package com.haofan.algorithm.offer.trie;

public class WordDictionary {

    private Trie root;

    public WordDictionary() {
        root = new Trie();
    }

    public void addWord(String word) {
        root.insert(word);
    }

    // 搜索的话要注意
    // 如果当前字符是字母，则判断当前字符对应的子节点是否存在，如果子节点存在则移动到子节点
    // 继续搜索下一个字符，如果子节点不存在则说明单词不存在，返回false
    // 如果当前节点是点号，由于点号可以表示任何字母，因此需要对当前节点的所有非空子节点继续搜索下一个字符
    public boolean search(String world) {
        return dfs(world, 0, root.getRoot());
    }

    private boolean dfs(String word, int index, Trie.TrieNode node) {
        if (index == word.length()) {
            return node.isWorld;
        }

        char ch = word.charAt(index);

        if (Character.isLetter(ch)) {
            int childIndex = ch - 'a';
            Trie.TrieNode child = node.children[childIndex];
            if (child != null && dfs(word, index + 1, child)) {
                return true;
            }
        } else {
            for (int i = 0; i < 26; i++) {
                // 因为是.，所以只要不是空就可以
                Trie.TrieNode child = node.children[i];
                if (child != null && dfs(word, index + 1, child)) {
                    return true;
                }
            }
        }
        return false;
    }
}
