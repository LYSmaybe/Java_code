package BinaryTree;

import java.util.*;

//层序遍历，并将每层都保存在一个数组中
public class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
}

public class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> Mat = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root != null){
            queue.offer(root);
        }
        //层序遍历
        while(!queue.isEmpty()){
            //取出队列中全部元素
            int size = queue.size();
            List<Integer> rowNode = new ArrayList();
            while(size > 0){
                TreeNode topNode = queue.poll();
                rowNode.add(topNode.val);
                if(topNode.left != null){
                    queue.offer(topNode.left);
                }
                if(topNode.right != null){
                    queue.offer(topNode.right);
                }
                --size;
            }
            Mat.add(rowNode);
        }
        return Mat;
    }
}