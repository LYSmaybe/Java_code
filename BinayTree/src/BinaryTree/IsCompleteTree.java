package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

//是否为完全二叉树
public class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    //构造函数
}

public class IsCompleteTree {
    public boolean isCompleteTree(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        if(root != null){
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            TreeNode topNode = queue.poll();
            //遇到空结点就开始停止遍历
            if(topNode == null)
                break;
            queue.offer(topNode.left);
            queue.offer(topNode.right);
        }
        //剩余全部出队，如果有非空结点，说明不是完全二叉树
        while(!queue.isEmpty()){
            TreeNode topNode = queue.poll();
            if (topNode != null) {
                return false;
            }
            return true;
        }
    }
}
