package com.haofan.algorithm.leetcodecn.medium.tree;

import com.haofan.algorithm.help.TreeNode;
import com.haofan.algorithm.leetcodecn.easy.tree.TreeSolution;

import java.util.*;

public class TreeSolutionMedium {

    public static void main(String[] args) {
        TreeSolutionMedium medium = new TreeSolutionMedium();
        TreeSolution easy = new TreeSolution();
        System.out.println("题目1：" + medium.inorderTraversal(TreeNode.buildTreeNode()));

        System.out.println("题目2：" + medium.zigzagLevelOrder(TreeNode.buildTreeNode()));

        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        System.out.println("题目3：");
        easy.prorderTraversalStack(medium.buildTree(preorder, inorder));

        System.out.println("题目4：");
        medium.connect(medium.buildTreeLinkNode());

        System.out.println("题目5: " + medium.kthSmallest(TreeNode.buildSearchTree(), 1));

        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
        };
        System.out.println("题目6：" + medium.numIslands(grid));
    }

    /**
     * 题目1：中序遍历二叉树
     * 思路：用栈实现
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }

    /**
     * 题目2：二叉树的锯齿形层次遍历
     * 思路：
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int j = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode first = queue.poll();
                temp.add(first.val);
                if (first.left != null) queue.offer(first.left);
                if (first.right != null) queue.offer(first.right);
            }
            if (j % 2 == 0) {
                Collections.reverse(temp);
            }
            j++;
            lists.add(temp);
        }
        return lists;
    }

    /**
     * 题目3：从前序遍历和中序遍历构造二叉树
     * https://www.cnblogs.com/xiagnming/archive/2018/09/07/9603925.html
     * 思路：前序遍历的第一个元素是根节点，然后找出改节点在中序遍历的位置，记为mid节点。在中序遍历中，mid节点
     * 左边的元素时左子树，右边的元素时右子树。依次递归
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) return null;
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] preorder, int pLeft, int pRight, int[] inorder, int iLeft, int iRight) {
        if (pLeft > pRight || iLeft > iRight) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pLeft]);
        int index = findPrivot(inorder, iLeft, iRight, preorder[pLeft]);
        int count = index - iLeft;
        root.left = buildTree(preorder, pLeft + 1, pLeft + 1 + count - 1, inorder, iLeft, index - 1);
        root.right = buildTree(preorder, pLeft + 1 + count, pRight, inorder, index + 1, iRight);
        return root;
    }

    public int findPrivot(int[] inorder, int left, int right, int target) {
        for (int i = left; i <= right; i++) {
            if (inorder[i] == target) return i;
        }
        return -1;
    }

    /**
     * 题目4：每个节点的右向指针
     * 要求用O(1)的空间复杂度，用两个指针start, cur, 其中start标记每一层的起始节点
     * cur用来遍历该层的节点
     */
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode start = root;
        TreeLinkNode cur = null;

        while (start.left != null) {
            cur = start;
            while (cur != null) {
                cur.left.next = cur.right;
                if (cur.next != null) cur.right.next = cur.next.left;
                cur = cur.next;
            }
            start = start.left;
        }
    }

    public TreeLinkNode buildTreeLinkNode() {
        TreeLinkNode root = new TreeLinkNode(10);
        root.left = new TreeLinkNode(9);
        root.right = new TreeLinkNode(20);
        root.right.left = new TreeLinkNode(15);
        root.right.right = new TreeLinkNode(7);
        return root;
    }

    /**
     * 题目5：二叉搜索树种的第K小的元素
     * 思路：利用中序遍历然后比较第K个最小元素
     */
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            while (--k == 0) {
                return root.val;
            }
            root = root.right;
        }
        return 0;
    }

    /**
     * 题目6：岛屿数量
     * 思路：广度优先遍历，先从第一个开始查，如果查到的是1，则一直遍历其上下左右的元素，并将遍历到的元素写成0，表示标记过。
     * 参考：https://www.bilibili.com/video/av68804700/
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int n = grid.length; // 行
        int m = grid[0].length; // 列
        int islands = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    markByBFS(grid, i, j);
                    islands++;
                }
            }
        }
        return islands;
    }

    private void markByBFS(char[][] grid, int x, int y) {
        // 分别定义上下左右四个元素
        int[] dirX = {0, 0, 1, -1};
        int[] dirY = {1, -1, 0, 0};

        Queue<Coordinate> queue = new LinkedList<>();
        queue.offer(new Coordinate(x, y));
        grid[x][y] = '0';
        while (!queue.isEmpty()) {
            Coordinate coordinate = queue.poll();
            for (int i = 0; i < 4; i++) {
                Coordinate obj = new Coordinate(coordinate.x + dirX[i], coordinate.y + dirY[i]);
                if (!inBound(obj, grid)) continue;
                if (grid[obj.x][obj.y] == '1') {
                    grid[obj.x][obj.y] = '0';
                    queue.offer(obj);
                }
            }
        }
    }

    private boolean inBound(Coordinate coordinate, char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        return coordinate.x >= 0 && coordinate.x < n && coordinate.y >= 0 && coordinate.y < m;
    }
}
