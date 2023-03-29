package com.haofan.algorithm.queue;

import com.haofan.algorithm.leetcodecn.easy.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QueueSolution {
    /**
     * 面试题1：滑动窗口的平均值
     *
     * 实现 MovingAverage 类：
     *
     * MovingAverage(int size) 用窗口大小 size 初始化对象。
     * double next(int val) 成员函数 next 每次调用的时候都会往滑动窗口增加一个整数，请计算并返回数据流中最后 size 个值的移动平均值，即滑动窗口里所有数字的平均值。
     */
    public static class MovingAverage {
        private Queue<Integer> nums;
        private int capacity;
        private int sum;

        public MovingAverage(int size) {
            nums = new LinkedList<>();
            capacity = size;
        }

        public double next(int val) {
            nums.offer(val);
            sum += val;
            if (nums.size() > capacity) {
                sum -= nums.poll();
            }
            return (double) sum / nums.size();
        }
    }

    /**
     * 面试题2：二叉树的广度优先遍历
     *
     * 用队列来解决二叉树的广度优先遍历，从上到下按层遍历二叉树，从二叉树的根节点开始，先遍历第一层，再遍历第二层。
     */
    public List<Integer> bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return result;
    }

    /**
     * 面试题3：在完全二叉树中添加节点
     *
     * 完全二叉树是每一层（除最后一层外）都是完全填充（即，节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。
     *
     * 实现数据结构 CBTInserter 有如下三种方法：
     *
     * CBTInserter (Node root) 使用头节点为 root 的给定树初始化该数据结构；
     *
     * CBTInserter->insert (val) 向树中插入一个新节点，节点类型为 TreeNode，值为 val 。使树保持完全二叉树的状态，并返回插入的新节点的父节点的值；
     *
     * CBTInserter.get_root () 将返回树的头节点。
     */
    public static class CBTInserter {
        private Queue<TreeNode> queue = new LinkedList<>();
        private TreeNode root;

        public CBTInserter(TreeNode root) {
            this.root = root;
            queue.offer(root);
            while (queue.peek().left != null && queue.peek().right != null) {
                // 按照广度优先顺序，从根节点开始遍历, 只有当一个节点的左右节点都存在时，才会将这个节点在queue删除并且将左右节点插入
                TreeNode node = queue.poll();
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        public int insert(int v) {
            TreeNode parent = queue.peek();
            TreeNode node = new TreeNode(v);

            if (parent.left == null) {
                // 左节点是空，直接赋值左节点
                parent.left = node;
            } else {
                // 左节点不是空，赋值右节点, 赋值完后，左右节点都不空，再删除queue的头元素。
                parent.right = node;

                queue.poll();
                queue.offer(parent.left);
                queue.offer(parent.right);
            }
            // 返回插入节点的父节点
            return parent.val;
        }

        public TreeNode getRoot() {
            return this.root;
        }
    }

    /**
     *  面试题4：二叉树每层的最大值
     *
     *  解法：
     *  就是通过广度优先遍历，每次遍历一层，遍历的同时，获取每一层的最大值，当一层遍历完了，就将最大值加入列表即可。
     */
    public List<Integer> largestValues(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        if (root != null) {
            queue.offer(root);
        }

        // 广度优先遍历
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode maxNode = new TreeNode(Integer.MIN_VALUE);
            // 每次遍历一层，同时获取每一层的最大值
            for (int i = 0; i< size; i++) {
                TreeNode node = queue.poll();
                maxNode = node.val > maxNode.val ? node : maxNode;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            // 将最大值添加到列表中
            result.add(maxNode.val);
        }
        return result;
    }

    /**
     * 面试题5：二叉树最底层最左边的值
     *
     * 解法: 使用bfs深度优先搜索遍历二叉树节点，需要使用队列辅助遍历，每次队列存储的节点都是同一层的节点，
     * 然后取出节点将节点的右子树加入到队列，然后再加入左子树到队列中，这样一直遍历到最后的一个节点就是最底层最左边的节点，
     * 所以可以实时更新遍历的节点为最左节点值，这样最后遍历结束的记录返回即可。
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        int val = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // 先遍历right，这个很重要
            if (node.right != null) {
                queue.offer(node.right);
            }

            if (node.left != null) {
                queue.offer(node.left);
            }
            val = node.val;
        }
        return val;
    }

    /**
     * 面试题5：二叉树的右侧视图
     *
     * 解法：
     * 广度优先搜索遍历每一层，然后找出每一层的最右侧的节点
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> view = new LinkedList<>();
        if (root == null) {
            return view;
        }
        // 当前遍历的层
        Queue<TreeNode> queue1 = new LinkedList<>();
        // 下一层的节点
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(root);
        while (!queue1.isEmpty()) {
            TreeNode node = queue1.poll();
            if (node.left != null) {
                queue2.offer(node.left);
            }

            if (node.right != null) {
                queue2.offer(node.right);
            }

            if (queue1.isEmpty()) {
                view.add(node.val);
                // 开始下一层
                queue1 = queue2;
                queue2 = new LinkedList<>();
            }
        }
        return view;
    }

}
