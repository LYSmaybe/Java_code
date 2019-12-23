package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

//层序遍历
public class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
}

public class LevelOrderTraversal {
    public void levelOrderTraversal(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        if(root != null)
            queue.offer(root);
        while(!queue.isEmpty()){
            //获取队列头元素
            TreeNode topNode = queue.poll();
            System.out.println(topNode.val);
            //入队孩子节点
            if(topNode.left != null){
                queue.offer(topNode.left);
            }
            if(topNode.right != null){
                queue.offer(topNode.right);
            }
        }
    }
}
