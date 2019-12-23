package BinaryTree;

//平衡二叉树
public class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
}

public class IsBalanced {
    public int getHeight(TreeNode root){
        return root == null ? 0 : Math.max(getHeight(root.left),getHeight(root.right))+1;
    }

    public boolean isBalanced(TreeNode root){
        if(root == null)
            return true;
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.abs(left - right) < 2 //abs绝对值
                && isBalanced((root.left))
                && isBalanced(root.right);
    }
}