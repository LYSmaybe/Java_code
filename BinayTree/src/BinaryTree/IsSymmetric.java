package BinaryTree;

//对称二叉树
public class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
}

//是否左右深度相等，且值对称相等
public class IsSymmetric {
    public boolean _isSymmetric(TreeNode left, TreeNode right){
        if(left == null && right == null)
            return true;
        if(left == null || right == null)
            return false;
        return left.val == right.val
                &&_isSymmetric(left.left, right.right)
                &&_isSymmetric(left.right, right.left);
    }

    public boolean isSymmetric(TreeNode root){
        if(root == null)
            return true;
        return _isSymmetric(root.left, root.right);
    }
}`