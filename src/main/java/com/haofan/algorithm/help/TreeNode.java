package com.haofan.algorithm.help;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    /**
     * Build tree Node
     * 10
     * / \
     * 9  20
     * / \
     * 15  7
     */
    public static TreeNode buildTreeNode() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        return root;
    }

    /**
     * Build Search Tree
     * 3
     * / \
     * 1  4
     * \
     * 2
     */
    public static TreeNode buildSearchTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        return root;
    }

    public static TreeNode build01Tree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(0);

        root.right = new TreeNode(0);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);
        return root;
    }

    /**
     *  二叉平衡树
     *     1
     *    / \
     *   2   3
     *  / \  /
     * 4   5 6
     */
    public static TreeNode buildCompleteTree() {
        // 示例：构建一个完全二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        return root;
    }
}
