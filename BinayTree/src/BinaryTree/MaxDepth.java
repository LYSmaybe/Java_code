package BinaryTree;

//判断最大深度
public class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
}

public class MaxDepth {
    public int maxDepth(TreeNode root){
        //return root == null ? 0 : Math.max(maxDepth(root.left),maxDepth(root.right))+1;
        if(root == null)
            return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return left > right ? left + 1 : right + 1;
    }
}
