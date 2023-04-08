package com.haofan.algorithm.offer.tree;

import com.haofan.algorithm.help.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeSolutionTest {
    private TreeSolution tree = new TreeSolution();

    @Test
    void recursionTraversal() {
        assertArrayEquals(new Object[]{9, 10, 15, 20, 7}, tree.recursionTraversal(TreeNode.buildTreeNode()).toArray());
    }

    @Test
    void inorderTraversal() {
        assertArrayEquals(new Object[]{9, 10, 15, 20, 7}, tree.inorderTraversal(TreeNode.buildTreeNode()).toArray());
    }

    @Test
    void sumNumbers() {
        assertEquals(2531, tree.sumNumbers(TreeNode.buildTreeNode()));
    }

    @Test
    void searchBST() {
        assertEquals(2, tree.searchBST(TreeNode.buildSearchTree(), 2).val);
    }

    @Test
    void inorderSuccessor() {
        assertEquals(null, tree.inorderSuccessor(TreeNode.buildSearchTree(), TreeNode.buildSearchTree().right));
    }

    @Test
    void convertBST() {
        assertEquals(7, tree.convertBST(TreeNode.buildSearchTree()).val);
    }

    @Test
    void BSTIterator() {
        TreeSolution.BSTIterator bstIterator = new TreeSolution.BSTIterator(TreeNode.buildSearchTree());
        assertTrue(bstIterator.hasNext());
        assertEquals(1, bstIterator.next());
    }

    @Test
    void findTarget() {
        assertFalse(tree.findTarget(TreeNode.buildSearchTree(), 2));
    }
}