package com.haofan.algorithm.offer.queue;

import com.haofan.algorithm.help.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QueueSolution {
    /**
     * 面试题41：滑动窗口的平均值
     * <p>
     * 实现 MovingAverage 类：
     * <p>
     * MovingAverage(int size) 用窗口大小 size 初始化对象。
     * double next(int val) 成员函数 next每次调用的时候都会往滑动窗口增加一个整数，请计算并返回数据流中最后 size 个值的移动平均值，即滑动窗口里所有数字的平均值。
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
     * 面试题42：二叉树的广度优先遍历
     *
     * <p>
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
     * 面试题43：在完全二叉树中添加节点
     * <p>
     * 完全二叉树是每一层, 除最后一层外, 都是完全填充（即，节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。
     * <p>
     * 更容易理解：
     * <a href="https://leetcode.cn/problems/NaqhDT/solutions/1689235/wang-wan-quan-er-cha-shu-tian-jia-jie-di-4wl4/">...</a>
     *
     * <p>
     * 实现数据结构 CBTInserter 有如下三种方法：
     * <p>
     * CBTInserter (Node root) 使用头节点为root 的给定树初始化该数据结构, 这里初始化的目的其实是为了能够选取出能作为candidate的节点
     * <p>
     * 对于一个完全二叉树，只有倒数第二层（如果存在）最右侧的若干个节点，以及最后一层的全部节点可以再添加子节点，其余的节点都已经拥有两个子节点。
     * 我们只需要在初始时找到这些节点即可。
     *
     * <p>
     * CBTInserter->insert (val) 向树中插入一个新节点，节点类型为 TreeNode，值为 val 。使树保持完全二叉树的状态，并返回插入的新节点的父节点的值；
     * <p>
     * CBTInserter.get_root () 将返回树的头节点。
     */
    public static class CBTInserter {
        private Queue<TreeNode> candidate = new LinkedList<>();
        private TreeNode root;

        public CBTInserter(TreeNode root) {
            this.root = root;

            // 广度优先遍历完全二叉树
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                // 其中有一个是空的, 则赋值给candidate
                if (!(node.left != null && node.right != null)) {
                    candidate.offer(node);
                }
            }
        }

        public int insert(int v) {
            TreeNode child = new TreeNode(v);
            TreeNode node = candidate.peek();
            int ret = node.val;
            if (node.left == null) {
                node.left = child;
            } else {
                node.right = child;
                candidate.poll();
            }

            candidate.offer(child);
            return ret;
        }

        public TreeNode getRoot() {
            return this.root;
        }
    }

    /**
     * 面试题43：二叉树每层的最大值
     * 方案1
     * <p>
     * 解法：
     * 就是通过广度优先遍历，每次遍历一层，遍历的同时，获取每一层的最大值，当一层遍历完了，就将最大值加入列表即可。
     * <p>
     * 当然也可以用两个队列来实现。
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
            for (int i = 0; i < size; i++) {
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
     * 面试题43：二叉树每层的最大值
     * 方案2: 比较容易理解
     * <p>
     * 解法：
     * 两个队列，queue1只存放当前遍历层的节点，queue2存放下一个层的节点。
     * <p>
     * 当然也可以用两个队列来实现。
     */
    public List<Integer> largestValues2(TreeNode root) {
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        if (root != null) {
            queue1.offer(root);
        }

        // 广度优先遍历
        int max = Integer.MIN_VALUE;
        while (!queue1.isEmpty()) {
            TreeNode node = queue1.poll();
            max = Math.max(max, node.val);

            if (node.left != null) {
                queue2.offer(node.left);
            }
            if (node.right != null) {
                queue2.offer(node.right);
            }

            // queue1 是空的
            if (queue1.isEmpty()) {
                result.add(max);
                max = Integer.MIN_VALUE;

                queue1 = queue2;
                queue2 = new LinkedList<>();
            }
        }
        return result;
    }


    /**
     * 面试题45：二叉树最底层最左边的值
     * <p>
     * 解法: 和上个题目类似，广度优先遍历，两个queue
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();

        if (root != null) {
            queue1.offer(root);
        }
        int bottomLeft = root.val;

        while (!queue1.isEmpty()) {
            TreeNode node = queue1.poll();
            // 先遍历right，这个很重要
            if (node.left != null) {
                queue2.offer(node.left);
            }

            if (node.right != null) {
                queue2.offer(node.right);
            }

            if (queue1.isEmpty()) {
                queue1 = queue2;
                queue2 = new LinkedList<>();
                if (!queue1.isEmpty()) {
                    bottomLeft = queue1.peek().val;
                }
            }
        }
        return bottomLeft;
    }

    /**
     * 面试题45：二叉树的右侧视图
     * <p>
     * 解法：
     * 广度优先搜索遍历每一层，然后找出每一层的最右侧的节点，也是两个queue.
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
                // 这个node 就是每一层的最后一个节点
                view.add(node.val);
                // 开始下一层
                queue1 = queue2;
                queue2 = new LinkedList<>();
            }
        }
        return view;
    }
}
