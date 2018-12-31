package tree;

import java.util.*;

/**
 * 二叉树问题：
 * 1. 用递归
 * 2. 用栈
 */
public class TreeSolution {
    /**
     * 题目1：二叉树的最大深度
     * 思路：递归实现，所谓递归要把它理解成高中数学中的递推公式，这一项的值和之前几项的关系
     * 所以二叉树的深度就是：从最底层开始求值，如果不是空则加1
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return left > right ? left + 1 : right + 1;
    }

    /**
     * 题目2： 验证二叉搜索树（左子树的节点值都小于node的值，右子树节点的值都大于node值）
     * 思路：https://blog.csdn.net/sgbfblog/article/details/7771096
     * 1. 暴力搜索法：搜索左子树是否小于node值，搜索右子树是否大于node值
     * 2. 利用中序遍历，一棵二叉搜索树的中序遍历后，其节点的值是从小到大排序的
     */
    public boolean isValidBSTRefursive(TreeNode root) {
        if (root == null) {
            return true;
        }
        int prev = Integer.MIN_VALUE;
        // 判断值是否都大于prev
        return isValidBSTHelper(root, prev);
    }

    public boolean isValidBSTStack(TreeNode root) {
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
            if (pre != null && root.val < pre.val) return false;
            pre = root;
            root = root.right;
        }
        return true;
    }

    /**
     * 题目3：对称二叉树，判断一个二叉树是否是镜像对称的
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricHelper(root.left, root.right);
    }

    private boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return (left.val == right.val) && (isSymmetricHelper(left.left, right.right))
                && (isSymmetricHelper(left.right, right.left));
    }

    private boolean isValidBSTHelper(TreeNode node, int prev) {
        if (node == null) {
            return true;
        }
        // 先遍历左子树, 节点值都大于prev
        if (isValidBSTHelper(node.left, prev)) {
            // 得到 node 节点值
            if (node.val > prev) {
                prev = node.val;
                return isValidBSTHelper(node.right, prev);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 题目4：二叉树的层次遍历
     * 思路：用队列实现，先进先出
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return result;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode first = queue.poll();
                temp.add(first.val);
                if (first.left != null) {
                    queue.offer(first.left);
                }
                if (first.right != null) {
                    queue.offer(first.right);
                }
            }
            result.add(temp);
        }
        return result;
    }

    /**
     * 题目5：将有序数组转成高度平衡的二叉搜索树（二叉树每个节点的左右两个子树的高度差的绝对值不超过1）
     * 思路：注意是升序的数组
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, start, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, end);
        return root;
    }

    /**
     * 题目附加1：二叉树的中序遍历, 递归法
     */
    public void inorderTraversalRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversalRecursive(root.left);
        System.out.print(root.val + " ");
        inorderTraversalRecursive(root.right);
    }

    /**
     * 题目附加2：二叉树的中序遍历，stack 方法
     */
    public List<Integer> inorderTraversalStack(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
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
     * 题目附加3：二叉树的前序遍历：
     */
    public void prorderTraversalStack(TreeNode root) {
        // 先序遍历
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            System.out.print(temp.val + " ");
            if (temp.right != null) stack.push(temp.right);
            if (temp.left != null) stack.push(temp.left); //后进先出，所以后压left
        }
    }

    /**
     * Build tree Node
     * 10
     * / \
     * 9  20
     *    / \
     *   15  7
     *
     * @return
     */
    public TreeNode buildTreeNode() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        return root;
    }

    public static void main(String args[]) {
        TreeSolution s = new TreeSolution();
        System.out.println("题目1：" + s.maxDepth(s.buildTreeNode()));

        System.out.println("题目2 递归：" + s.isValidBSTRefursive(s.buildTreeNode()));

        System.out.println("题目2 非递归：" + s.isValidBSTStack(s.buildTreeNode()));

        System.out.print("题目附加1：");
        s.inorderTraversalRecursive(s.buildTreeNode());
        System.out.println();

        System.out.println("题目附加2：" + s.inorderTraversalStack(s.buildTreeNode()));

        System.out.println("题目附加3：");
        s.prorderTraversalStack(s.buildTreeNode());

        System.out.println("\n题目3： " + s.isSymmetric(s.buildTreeNode()));

        System.out.println("题目4：" + s.levelOrder(s.buildTreeNode()));

        int[] nums = {-10, -3, 0, 5, 9};
        System.out.println("题目5：" + s.sortedArrayToBST(nums).val);
    }
}
