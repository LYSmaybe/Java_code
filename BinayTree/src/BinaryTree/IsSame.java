package BinaryTree;

import java.util.List;

//判断子树是否相等
public class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
}

public class IsSame{
    public boolean isSame(TreeNode root1, TreeNode root2){
        if(root1==null && root2==null){
            return true;
        }
        if(root1==null || root2==null){
            return false;
        }
        if(root1.val == root2.val)
            return isSame(root1.left,root2.left)&&isSame(root1.right, root2.right);
    }

    public boolean isSubtree(TreeNode root1, TreeNode root2){
        if(root1 == null)
            return false;
        if(isSame(root1, root2))
            return true;
        if(isSubtree(root1.left, root2))
            return true;
        return isSubtree(root1.right, root2);
    }
}
