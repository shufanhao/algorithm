package com.haofan.algorithm.offer.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeSolutionTest {
    private TreeSolution tree = new TreeSolution();
    private com.haofan.algorithm.leetcodecn.easy.tree.TreeSolution treeTool = new com.haofan.algorithm.leetcodecn.easy.tree.TreeSolution();

    @Test
    void recursionTraversal() {
        assertArrayEquals(new Object[]{9, 10, 15, 20, 7}, tree.recursionTraversal(treeTool.buildTreeNode()).toArray());
    }

    @Test
    void inorderTraversal() {
        assertArrayEquals(new Object[]{9, 10, 15, 20, 7}, tree.inorderTraversal(treeTool.buildTreeNode()).toArray());
    }

    @Test
    void sumNumbers() {
        assertEquals(2531, tree.sumNumbers(treeTool.buildTreeNode()));
    }

    @Test
    void searchBST() {
        assertEquals(2, tree.searchBST(treeTool.buildSearchTree(), 2).val);
    }

    @Test
    void inorderSuccessor() {
        assertEquals(null, tree.inorderSuccessor(treeTool.buildSearchTree(), treeTool.buildSearchTree().right));
    }

    @Test
    void convertBST() {
        assertEquals(7, tree.convertBST(treeTool.buildSearchTree()).val);
    }

    @Test
    void BSTIterator() {
        TreeSolution.BSTIterator bstIterator = new TreeSolution.BSTIterator(treeTool.buildSearchTree());
        assertTrue(bstIterator.hasNext());
        assertEquals(1, bstIterator.next());
    }

    @Test
    void findTarget() {
        assertFalse(tree.findTarget(treeTool.buildSearchTree(), 2));
    }
}