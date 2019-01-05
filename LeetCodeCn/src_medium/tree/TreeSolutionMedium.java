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

    /**
     * 题目3：从前序遍历和中序遍历构造二叉树
     * https://www.cnblogs.com/xiagnming/archive/2018/09/07/9603925.html
     * 思路：前序遍历的第一个元素是根节点，然后找出改节点在中序遍历的位置，记为mid节点。在中序遍历中，mid节点
     * 左边的元素时左子树，右边的元素时右子树。依次递归
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) return null;
        return buildTree(preorder, 0, preorder.length -1, inorder, 0, inorder.length -1);
    }

    public TreeNode buildTree(int[] preorder, int pLeft, int pRight, int[] inorder, int iLeft, int iRight) {
        if (pLeft > pRight || iLeft > iRight) { return null; }
        TreeNode root = new TreeNode(preorder[pLeft]);
        int index = findPrivot(inorder, iLeft, iRight, preorder[pLeft]);
        int count = index - iLeft;
        root.left = buildTree(preorder, pLeft + 1, pLeft + 1 + count - 1, inorder, iLeft, index - 1);
        root.right = buildTree(preorder, pLeft + 1 + count, pRight, inorder, index + 1, iRight);
        return root;
    }

    public int findPrivot(int[] inorder, int left, int right, int target) {
        for (int i = left; i<= right; i++) {
            if (inorder[i] == target) return i;
        }
        return -1;
    }
    public static void main(String args[]) {
        TreeSolutionMedium medium = new TreeSolutionMedium();
        TreeSolution easy = new TreeSolution();
        System.out.println("题目1：" + medium.inorderTraversal(easy.buildTreeNode()));

        System.out.println("题目2：" + medium.zigzagLevelOrder(easy.buildTreeNode()));

        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        System.out.println("题目3：" );
        easy.prorderTraversalStack(medium.buildTree(preorder, inorder));

    }
}
