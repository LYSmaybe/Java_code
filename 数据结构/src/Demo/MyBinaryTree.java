package Demo;

import java.util.LinkedList;
import java.util.Queue;

public class MyBinaryTree<E> {
    protected int size;
    protected Node<E> root;

    protected static class Node<E>{ //在内部创建“结点内部类”
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent){
            this.element = element;
            this.parent = parent;
        }

        //封装的两个常用判断方法
        //是否为叶子节点
        public boolean isLeaf(){
            return left == null && right == null;
        }

        //是否有两个孩子
        public boolean hasTwoChildren(){
            return left != null && right != null;
        }
    }

    //前序遍历
    public void preorderTraversal(){ //使用递归实现前序遍历
        preorderTraversal(root);
    }
    private void preorderTraversal(Node<E> node){
        if(node == null) return;
        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    //中序遍历
    public void inorderTraversal(){
        inorderTraversal(root);
    }
    private void inorderTraversal(Node<E> node){
        if(node == null) return;
        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }

    //后序遍历
    public void postorderTraversal(){
        inorderTraversal(root);
    }
    private void postorderTraversal(Node<E> node){
        if(node == null) return;
        inorderTraversal(node.left);
        inorderTraversal(node.right);
        System.out.println(node.element);

    }

    //层序遍历：使用队列实现
    public void levelOrderTraversal(){
        if(root == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            Node<E> node = queue.poll(); //每次循环的为：新出队的结点
            System.out.println(node.element);

            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
    }


    //设计遍历的接口，使遍历个性化：
    //层序遍历
    public static interface Visitor<E> { //内部接口设计
        void visit(E element);
    }

    public void levelOrder(Visitor<E> visitor){
        if(root == null || visitor == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            Node<E> node = queue.poll(); //每次循环的为：新出队的结点
            //System.out.println(node.element);
            visitor.visit(node.element); //调用接口的方法，将遍历的每一个值都传出去，具体要干什么由外部决定

            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
    }

    //前序遍历
    public void preorder(Visitor<E> visitor){
        preorder(root, visitor);
    }
    private void preorder(Node<E> node, Visitor<E> visitor){
        if(root == null || visitor == null) return;

        visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    //中序遍历
    public void ineorder(Visitor<E> visitor){
        ineorder(root, visitor);
    }
    private void ineorder(Node<E> node, Visitor<E> visitor){
        if(root == null || visitor == null) return;

        ineorder(node.left, visitor);
        visitor.visit(node.element);
        ineorder(node.right, visitor);
    }

    //后序遍历
    public void postorder(Visitor<E> visitor){
        postorder(root, visitor);
    }
    private void postorder(Node<E> node, Visitor<E> visitor){
        if(root == null || visitor == null) return;

        postorder(node.left, visitor);
        postorder(node.right, visitor);
        visitor.visit(node.element);
    }

    //寻找二叉树的前驱节点
    protected Node<E> predecessor(Node<E> node){
        if(node == null) return null;

        //node有左子树时：前驱节点是其左子树的最右结点，（node.left.right.right...)
        Node<E> p = node.left;
        if(p != null){
            while (p.right != null){
                p = p.right;
            }
            return p;
        }
        //node没有左子树时：前驱节点是比其小的父节点，即如果 node 是其父节点的右子树，该父节点为前驱节点
        while(node.parent != null && node == node.parent.left){
            node = node.parent;
        }
        //此时：node.parent 为 null（没找到，返回null）；node是其父节点的右子树（找到了，返回node）
        return node.parent;
    }

    //寻找二叉树的后继结点
    protected Node<E> successor(Node<E> node){
        if(node == null) return null;

        //node有右子树时：后继结点是右子树的最左结点
        Node<E> p = node.right;
        if(p != null){
            while(p.left != null){
                p = p.left;
            }
            return p;
        }
        //node 没有右子树：后继结点是比其大的父节点，即如果 node 是其父节点的左子树，该父节点为后继节点
        while (node.parent != null && node == node.parent.right){
            node = node.parent;
        }
        return node.parent;
    }

    //是否为完全二叉树（两种方法，1更好一些）
    public boolean isComplete1(){
        if(root == null) return false;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while(!queue.isEmpty()){
            Node<E> node = queue.poll();
            if(leaf && node.isLeaf()) return false;

            //在左节点不为空的情况下入队其子节点，再看右节点是否为空
            if(node.left != null){
                queue.offer(node.left);
            }else if(node.right != null){ //此时左节点为空，但右节点不为空，直接返回 false
                return false;
            }
            //在右节点不为空的情况下入队其子节点，再看左节点是否为空
            if(node.right != null){
                queue.offer(node.right);
            }else {
                leaf = true;
            }
        }
        return true;
    }

    public boolean isComplete2(){
        if(root == null) return false;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false; //此处要求为叶子节点
        while(!queue.isEmpty()){
            Node<E> node = queue.poll();
            if(leaf && !node.isLeaf()) return false;

            if(node.hasTwoChildren()){ //有双孩子：有左节点和右节点
                queue.offer(node.left);
                queue.offer(node.right);
            }else if(node.left == null && node.right != null){ //左空右不空，肯定不是
                return false;
            }else { //排除前面情况之后，后面遍历的结点都为叶子节点了，leaf 改为 true
                leaf = true; //走到这个情况，后面的结点必须都是叶子，所以为下一次拿到的结点增加了一个判断条件
                if(node.left != null){
                    queue.offer(node.left);
                }
            }
        }
        return true;
    }

    //二叉树的高度：递归与迭代
    //1. 递归
    public int height1(){
        return height1(root);
    }
    private int height1(Node<E> node){
        if(node == null) return 0;

        return 1 + Math.max(height1(node.left), height1(node.right));
    }
    //2. 迭代(使用层序遍历思想，利用队列)
    public int height2(){
        if(root == null) return 0;

        int height = 0;
        int levelSize = 1; //存储遍历的每一层的元素个数
        Queue<Node <E>> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            Node<E> node = queue.poll(); //队列中取出的元素进行遍历
            levelSize--; //每层取出一个时，此层元素数-1
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
            if(levelSize == 0){
                levelSize = queue.size(); //更新 levelsize 的值
                height++;
            }
        }
        return height;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        root = null;
        size = 0;
    }
}