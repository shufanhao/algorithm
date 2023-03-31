package com.haofan.algorithm.offer.tree;

import com.haofan.algorithm.leetcodecn.easy.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TreeSolution {
    /**
     * 面试题1：二叉树的广度优先遍历。中序，前序和后序遍历 用递归法
     *
     * 中序，前序和后序遍历是指的，什么时候遍历根节点
     * 中序: left, root, right
     * 前序: root, left, right
     * 后序: left, right, root
     */
    public List<Integer> recursionTraversal(TreeNode root) {
        List<Integer> nodes = new LinkedList<>();
        dfs(root, nodes);
        return nodes;
    }
    private void dfs(TreeNode root, List<Integer> nodes) {
        if (root != null) {
            // 中序遍历
            dfs(root.left, nodes);
            nodes.add(root.val);
            dfs(root.right, nodes);

            // 前序遍历
            /*nodes.add(root.val);
            dfs(root.left, nodes);
            dfs(root.right, nodes);

            // 后序遍历
            dfs(root.left, nodes);
            dfs(root.right, nodes);
            nodes.add(root.val);*/
        }
    }

    /**
     * 面试题2：二叉树的广度优先遍历。中序，前序和后序遍历 用迭代法
     *
     * 把递归代码改写成迭代的代码常用到stack，
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> nodes = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // 中序遍历
            while (cur != null) {
                //  一直到最深的左节点
                stack.push(cur);
                cur = cur.left;
            }
            // 弹出最左节点，因为后入先出原则。
            cur = stack.pop();
            nodes.add(cur.val);
            cur = cur.right;

            // 如果是前序便利的话
            /**
            while (cur != null) {
                nodes.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            cur = cur.right;
             **/
        }
        return nodes;
    }

    /**
     * 面试题3：二叉树剪枝
     * 给你二叉树的根结点 root ，此外树的每个结点的值要么是 0 ，要么是 1 。
     * 返回移除了所有不包含 1 的子树的原二叉树。
     *
     * 解法：什么样的树可以剪枝 ？ 本节点是0，并且其所有子节点也是0的二叉树。
     *
     * 正好适合后序遍历
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        // refer: 后序遍历
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0) {
            // 删掉该元素
            return null;
        }
        return root;
    }

    /**
     * 面试题4：从根节点到叶节点的路径数字之和
     *
     * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。每条从根节点到叶节点的路径都代表一个数字：
     *
     * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。计算从根节点到叶节点生成的 所有数字之和。
     *
     * 解法：二叉树前序遍历即可。
     */
    public int sumNumbers(TreeNode root) {
        return dfsSum(root, 0);
    }

    private int dfsSum(TreeNode root, int path) {
        if (root == null) {
            return 0;
        }

        path = path * 10 + root.val;
        if (root.left == null && root.right == null) {
            return path;
        }

        return dfsSum(root.left, path) + dfsSum(root.right, path);
    }

    /**
     * 面试图5：二叉搜索树查找
     *
     * 如果二叉树的高度是H，则查找的时间复杂度是O(H)
     */
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode cur = root;

        while (cur != null) {
            if (cur.val == val) {
                break;
            }
            if (cur.val < val) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return cur;
    }

    /**
     * 面试图6：展平二叉搜索树
     *
     * 将一个二叉搜索树，展平
     *
     * 解法：其实就是一个中序遍历，改改。
     */
    public TreeNode increaseBST(TreeNode root) {
        // Todo
        return null;
    }
}
