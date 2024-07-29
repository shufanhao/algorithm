package com.haofan.algorithm.offer.tree;


import com.haofan.algorithm.help.ListNode;
import com.haofan.algorithm.help.TreeNode;
import com.sun.source.tree.Tree;

import java.util.*;

public class TreeSolution {
    /**
     * 面试题1：二叉树的广度优先遍历。中序，前序和后序遍历 用递归法
     * <p>
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
     * <p>
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
        }
        /** 如果是前序遍历
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            // 注意压栈顺序，先右后左
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        **/
        return nodes;
    }
    // 如果是前序遍历的话

    /**
     * 验证是否是一个二叉搜索树
     *
     * <a href="https://leetcode.cn/problems/validate-binary-search-tree/">...</a>
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null; //
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                // 遍历左子树
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();// 取到最后一个节点
            if (pre != null && root.val <= pre.val) return false;
            pre = root;
            root = root.right;
        }
        return true;
    }

    /**
     * 前序遍历，中序遍历，后序遍历更加容易的解法：
     * https://leetcode.cn/problems/binary-tree-inorder-traversal/solutions/25220/yan-se-biao-ji-fa-yi-chong-tong-yong-qie-jian-ming/
     * 前序：加入栈的顺序是右左根（根左右的反向）。
     * 中序，加入栈的顺序是右根左（左根右的反向）。
     * 后序，加入栈的顺序是根右左（左右根的反向）。
     *
     * 思路：
     * 使用颜色标记节点的状态，新节点为白色，已访问的节点为灰色。
     * 如果遇到的节点为白色，则将其标记为灰色，然后将其右子节点、自身、左子节点依次入栈，中序遍历
     * 如果遇到的节点为灰色，则将节点的值输出。
     *
     * 这种方法本质上是给 TreeNode 类添加了一个访问控制变量，对于没访问过的节点，先放入栈中，第二次访问的时候再出栈。
     */
    public List<Integer> middleTraversal(TreeNode root) {
        if(root == null) return new ArrayList<Integer>();

        List<Integer> res = new ArrayList<>();
        Stack<ColorNode> stack = new Stack<>();
        stack.push(new ColorNode(root,"white"));

        while(!stack.empty()){
            ColorNode cn = stack.pop();
            if(cn.color.equals("white")){
                // 中序遍历
                if(cn.node.right != null) stack.push(new ColorNode(cn.node.right,"white"));
                stack.push(new ColorNode(cn.node,"gray"));
                if(cn.node.left != null)stack.push(new ColorNode(cn.node.left,"white"));
                // 前序遍历 右左根
                // 后序遍历 根右左
            } else {
                res.add(cn.node.val);
            }
        }

        return res;
    }
    // 前序遍历
    public List<Integer> beforeTraversal(TreeNode root) {
        if(root == null) return new ArrayList<Integer>();

        List<Integer> res = new ArrayList<>();
        Stack<ColorNode> stack = new Stack<>();
        stack.push(new ColorNode(root,"white"));

        while(!stack.empty()){
            ColorNode cn = stack.pop();
            if(cn.color.equals("white")){
                if(cn.node.right != null) stack.push(new ColorNode(cn.node.right,"white"));
                if(cn.node.left != null)stack.push(new ColorNode(cn.node.left,"white"));
                stack.push(new ColorNode(cn.node,"gray"));
                // 前序遍历 右左根
                // 后序遍历 根右左
            } else {
                res.add(cn.node.val);
            }
        }

        return res;
    }
    // 后序遍历
    public List<Integer> afterTraversal(TreeNode root) {
        if(root == null) return new ArrayList<Integer>();

        List<Integer> res = new ArrayList<>();
        Stack<ColorNode> stack = new Stack<>();
        stack.push(new ColorNode(root,"white"));

        while(!stack.empty()){
            ColorNode cn = stack.pop();
            if(cn.color.equals("white")){
                stack.push(new ColorNode(cn.node,"gray"));
                if(cn.node.right != null) stack.push(new ColorNode(cn.node.right,"white"));
                if(cn.node.left != null)stack.push(new ColorNode(cn.node.left,"white"));
            } else {
                res.add(cn.node.val);
            }
        }

        return res;
    }

    static class ColorNode {
        TreeNode node;
        String color;

        public ColorNode(TreeNode node,String color){
            this.node = node;
            this.color = color;
        }
    }

    /**
     * 面试题47：二叉树剪枝
     * 给你二叉树的根结点 root ，此外树的每个结点的值要么是 0 ，要么是 1 。
     * 返回移除了所有不包含 1 的子树的原二叉树。
     * <p>
     * 解法：什么样的树可以剪枝? 本节点是0，并且其所有子节点也是0的二叉树。
     * <p>
     * 正好适合后序遍历。后序遍历的时候，如果每遍历到一个节点，如果左右子树是空的，并且该节点是0，则可以置为空
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
     * 面试题49：从根节点到叶节点的路径数字之和
     * <p>
     * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。每条从根节点到叶节点的路径都代表一个数字：
     * <p>
     * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。计算从根节点到叶节点生成的 所有数字之和。
     * <p>
     * 解法：二叉树前序遍历即可。但是有点儿不太好理解呢
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
     * <p>
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
     * 面试图52：展平二叉搜索树
     * <p>
     * 将一个二叉搜索树，展平
     * <p>
     * 解法：其实就是一个中序遍历，改改。
     * /**
     *      * Build Search Tree
     *      *  3
     *      * / \
     *      *1  4
     *      * \
     *      *  2
     *      */
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) return null;

        Stack<ColorNode> stack = new Stack<>();

        TreeNode dummy = new TreeNode(0);
        TreeNode curr = dummy;

        stack.push(new ColorNode(root, "white"));

        while (!stack.isEmpty()) {
            ColorNode node = stack.pop();
            if (node.color.equals("white")) {
                // 中序遍历: left, node, right
                if (node.node.right != null) stack.push(new ColorNode(node.node.right, "white"));
                stack.push(new ColorNode(node.node, "gray"));
                if (node.node.left != null) stack.push(new ColorNode(node.node.left, "white"));
            } else {
                curr.right = new TreeNode(node.node.val);
                curr = curr.right;
            }
        }
        return dummy.right;
    }

    // 或者用典型的中序遍历，空间复杂度比较低
    public TreeNode increasingBST1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode prev = null;
        TreeNode first = null;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (prev != null) {
                prev.right = cur;
            } else {
                first = cur;
            }

            prev = cur;
            cur.left = null;
            cur = cur.right;
        }

        return root;
    }
    /**
     * 面试题53：二叉搜索树的下一个节点
     * 其实就是找到中序遍历的对应的节点的，下一个节点。
     * <p>
     * 解法1：通过stack的方式进行中序遍历, 直到找到节点，时间复杂度O(N)
     * 解法2：因为是平衡二叉树，所以left < root < right, 所以二叉树的下一个节点，一定是right tree中最小的一个, 时间：O(h), 空间O(1)
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode cur = root;
        TreeNode result = null;
        while (cur != null) {
            if (cur.val < p.val) {
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
     * 面试题54：所有大于或等于节点的值之和
     * 给定一个二叉搜索树，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。
     * <p>
     * 解法1：先遍历一遍二叉树，然后拿到sum，然后中序遍历二叉搜索树，并记录之前所有节点的值，然后将该节点赋值成sum - total
     * 解法2：可以颠倒遍历二叉搜索树，先遍历right tree -> root -> left tree，这样就拿到了比该节点的大的和。
     */
    public TreeNode convertBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        int sum = 0;
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

    public TreeNode convertBST1(TreeNode root) {
        // 递归实现
        int[] sum = {0};
        return root;
    }

    /**
     * 面试题55: 二叉搜索树迭代器
     * <p>
     * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
     * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。
     * 指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
     * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
     * int next()将指针向右移动，然后返回指针处的数字。
     * <p>
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
     * 面试题56：二叉搜索树的两个节点值之和
     * <p>
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
     *
     *平衡二叉搜索树：任何两个子树的高度最大差别为1。这种特性确保了树的搜索、插入、删除等操作的时间复杂度保持在O(log n)
     */

    /**
     * 面试题114 二叉树展开为链表
     *
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     * <a href="https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/">...</a>
     *
     * 思路: 先序遍历结果后，然后更新left和right
     */
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preorderTraversal(root, list);
        int size = list.size();
        preorderTraversal(root, list);

        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1);
            TreeNode curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }
    private void preorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preorderTraversal(root.left, list);
            preorderTraversal(root.right, list);
        }
    }


    /**
     * 面试题226: 翻转二叉树
     * <a href="https://leetcode.cn/problems/invert-binary-tree/">...</a>
     *
     * 给一颗二叉树的根节点，要求翻转二叉树
     *
     * 解题：
     * 最常想到的思路是递归，第一次是自己做出来。
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode node = root;

        invertTreeRecursive(node);

        return root;
    }
    private void invertTreeRecursive(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        invertTreeRecursive(node.left);
        invertTreeRecursive(node.right);
    }

    /**
     * 面试题236: 二叉树的最近公共祖先
     * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree
     *
     * 思路:
     * 1. 递归。不是很容易理解。
     * 2. 迭代法。用哈希表，存储所有节点的父节点，然后利用节点的父节点信息，从P开始不断地向上找，
     *    并记录访问过的节点，再从q节点不断往上找，如果找到已经存在的节点，则说明是最近公共祖先。
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<Integer, TreeNode> parent = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        // dfs 遍历所有节点，然后构建parent map, key当前节点，value是父节点。
        dfs(root, parent);

        // 遍历p，并且把访问过的节点都放在visited set 中
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }

        // 遍历q，如果有访问过的节点，则说明是公共祖先。
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }

        return null;
    }

    private void dfs(TreeNode root, Map<Integer, TreeNode> parent) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left, parent);
        }

        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right, parent);
        }
    }
}
