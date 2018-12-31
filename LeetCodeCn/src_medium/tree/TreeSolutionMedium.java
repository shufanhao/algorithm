package tree;

import java.util.*;

public class TreeSolutionMedium {

    /**
     * 题目1：中序遍历二叉树
     * 思路：用栈实现
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null ) {
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
        int j = 1 ;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i=0; i<size; i++) {
                TreeNode first = queue.poll();
                temp.add(first.val);
                if (first.left != null) queue.offer(first.left);
                if (first.right != null) queue.offer(first.right);
            }
            if (j%2 == 0) {
                Collections.reverse(temp);
            }
            j++;
            lists.add(temp);
        }
        return lists;
    }

    public static void main(String args[]) {
        TreeSolutionMedium medium = new TreeSolutionMedium();
        TreeSolution easy = new TreeSolution();
        System.out.println("题目1：" + medium.inorderTraversal(easy.buildTreeNode()));

        System.out.println("题目2：" + medium.zigzagLevelOrder(easy.buildTreeNode()));

    }
}
