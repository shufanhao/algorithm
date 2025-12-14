package com.haofan.algorithm.offer.tree;

import com.haofan.algorithm.help.TreeNode;
import com.sun.source.tree.Tree;
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
    void middleTraversal() {
        assertArrayEquals(new Object[]{9, 10, 15, 20, 7}, tree.middleTraversal(TreeNode.buildTreeNode()).toArray());
    }

    @Test
    void beforeTraversal() {
        assertArrayEquals(new Object[]{10, 9, 20, 15, 7}, tree.beforeTraversal(TreeNode.buildTreeNode()).toArray());
    }

    @Test
    void afterTraversal() {
        assertArrayEquals(new Object[]{9, 15, 7, 20, 10}, tree.afterTraversal(TreeNode.buildTreeNode()).toArray());
    }

    @Test
    void pruneTree1() {
        TreeNode root = tree.pruneTree(TreeNode.build01Tree());
        assertNull(root.left);
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
    void increasingBST() {
        TreeNode root = tree.increasingBST1(TreeNode.buildSearchTree());
        assertEquals(1, root.val);
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

    @Test
    void flatten() {
        TreeNode root = TreeNode.buildSearchTree();
        tree.flatten(root);
        assertNull(root.left);
        assertEquals(3, root.val);
    }

    @Test
    void invertTree() {
        tree.invertTree(TreeNode.buildTreeNode());
    }

    @Test
    void lowestCommonAncestor() {
        TreeNode root = TreeNode.buildTreeNode();
        TreeNode common = tree.lowestCommonAncestor(TreeNode.buildTreeNode(), root.right.left, root.right.right);
        assertEquals(common.val, 20);
    }

    @Test
    void pathSum() {
        TreeNode root = TreeNode.buildTreeNode();
        assertEquals(1, tree.pathSum(root, 27));
    }

    @Test
    void diameterOfBinaryTree() {
        assertEquals(3, tree.diameterOfBinaryTree(TreeNode.buildTreeNode()));
    }

    @Test
    void mergeTrees() {
        assertEquals(20, tree.mergeTrees(TreeNode.buildTreeNode(), TreeNode.buildTreeNode()).val);
    }

    @Test
    void isSameTree() {
        assertTrue(tree.isSameTree(TreeNode.buildTreeNode(), TreeNode.buildTreeNode()));
    }

    @Test
    void hasPathSum() {
        assertTrue(tree.hasPathSum(TreeNode.buildTreeNode(), 19));
    }

    @Test
    void buildTree() {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        tree.buildTreeFromPreOrderInOrder(preorder, inorder);
    }

    @Test
    void buildTreeFromInOrderPostOrder() {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};

        tree.buildTreeFromInOrderPostOrder(inorder, postorder);
    }

    @Test
    void countNodes() {
        assertEquals(6, tree.countNodes(TreeNode.buildCompleteTree()));
    }

    @Test
    void getMinimumDifference() {
        tree.getMinimumDifference(TreeNode.buildSearchTree());
    }

    @Test
    void kthSmallest() {
        assertEquals(2, tree.kthSmallest(TreeNode.buildSearchTree(),2));
    }
}