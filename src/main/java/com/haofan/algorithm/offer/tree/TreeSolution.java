package com.haofan.algorithm.offer.tree;

import com.haofan.algorithm.leetcodecn.easy.tree.TreeNode;

import java.util.*;

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

    /**
     * 面试题7：二叉搜索树的下一个节点
     * 其实就是找到中序遍历的对应的节点的，下一个节点。
     *
     * 解法1：通过stack的方式进行中序遍历, 知道找到节点，时间复杂度O(N)
     * 解法2：因为是平衡二叉树，所以left < root < right, 所以二叉树的下一个节点，一定是right tree中最小的一个
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode cur = root;
        TreeNode result = null;
        while (cur != null) {
            if (cur.val < p.val) {
                // 找left tree
                cur = cur.right;
            } else {
                cur = cur.left;
                // 下面这段，在剑指offer书中，说的是要在上面。
                result = cur;
            }
        }
        return result;
    }

    /**
     * 面试题7：所有大于或等于节点的值之和
     * 给定一个二叉搜索树，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。
     *
     * 解法1：先遍历一遍二叉树，然后拿到sum，然后中序遍历二叉搜索树，并记录之前所有节点的值，然后将该节点赋值成sum - total
     * 解法2：可以颠倒遍历二叉搜索树，先遍历right tree -> root -> left tree，这样就拿到了比该节点的大的和。
     */
    public TreeNode convertBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        int sum =0;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                // traverse right tree instead of left tree
                cur = cur.right;
            }
            cur = stack.pop();
            sum += cur.val;
            cur.val = sum;
            cur = cur.left;
        }

        return root;
    }

    /**
     * 面试题8: 二叉搜索树迭代器
     *
     * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
     * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。
     * 指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
     * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
     * int next()将指针向右移动，然后返回指针处的数字。
     *
     * 解法1：将二叉树展平, 只有right node
     * 解法2：中序遍历的while循环条件，if tree, 每执行一次，就是遍历一个node。if false, 就是都遍历完了。
     * 所以：中序遍历中while循环条件可以看成迭代器的haxNext方法
     */
    public static class BSTIterator {
        TreeNode cur;
        Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            cur = root;
            stack = new Stack<>();
        }

        public boolean hasNext() {
            return cur != null || !stack.isEmpty();
        }

        public int next() {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            int val = cur.val;
            cur = cur.right;
            return val;
        }
    }

    /**
     * 面试题9：二叉搜索树的两个节点值之和
     *
     * 解法1: 利用hash 表，保存节点的值v，没遍历到一个节点，就看有没有存在k -v 的值
     * 解法2：双指针，将二叉搜索树看成一个排序数组，按照排序数组的解法。稍微麻烦
     */
    // 解法1
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (set.contains(k - cur.val)) {
                return true;
            }
            set.add(cur.val);
            cur = cur.right;
        }

        return false;
    }

    /**
     * 面试题：TreeSet, TreeMap
     */

}
