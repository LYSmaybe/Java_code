package MyBinarySearchTree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("unchecked")
public class MyBinarySearchTree<E> { //extends Comparable：即传入的元素必须实现可比较的接口（不能写死在这里，所以通过判断写在了后面）
                                                        //也可以写一个比较器接口，实现方法时可以变换逻辑
    private int size;
    private Node<E> root;
    private Comparator<E> comparator; //构造比较器属性

    public MyBinarySearchTree(){ //创建二叉搜索树时，就传入比较器构造方法
        this(null); //调用已有的构造方法，传入 null，即没有传入比较器
    }

    public MyBinarySearchTree(Comparator<E> comparator){ //创建二叉搜索树时，就传入比较器构造方法
        this.comparator = comparator;
    }

    private static class Node<E>{ //在内部创建“结点内部类”
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

        public boolean hasTwoChildren(){
            return left != null && right != null;
        }

        //是否有两个孩子
    }


    //方法实现
    public void add(E element){
        elementNotNullCheck(element);
        //判断:添加的是根节点（即第一个结点）
        if(root == null){
            root = new Node<>(element, null);
            size++; //一定不要忘记size
            return;
        }

        //添加的不是第一个结点

        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;
        while(node != null) {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else { // 此处为相等情况
                return;
            }
        }

            //将待插入元素创建成新的节点，进行插入
            Node<E> newNode = new Node<>(element, parent);
            if(cmp > 0){
                parent.right = newNode;
            }else{
                parent.left = newNode;
            }
            size++;
    }

    //禁止输入null：输入数据是否为null的检测
    private void elementNotNullCheck(E element){ //由于二叉树中不能有空元素，所以要有检查插入数据是否为空的操作
        if(element == null){
            throw new IllegalArgumentException("传入的数据不能为空");
        }
    }

    //比较 e1 和 e2 的大小
    private int compare(E e1, E e2){
        //return e1.compareTo(e2); //利用泛型中自己实现的 compareTo 方法
        if(comparator != null){ //分类讨论是否传入了个性化比较器
            return comparator.compare(e1, e2); //使用传入的 comparator 中的 comparator 方法？？？前一个 comparator 是啥？
        }
        //当没有传入个性化比较器时，类型强制转换后再使用 compareTo 方法
        return ((Comparable<E>) e1).compareTo(e2);
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

    //寻找二叉树的前驱节点
    private Node<E> predecessor(Node<E> node){
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
    private Node<E> successor(Node<E> node){
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

    //删除某节点
    public void remove(E element){ //首先要获取元素的结点，再进行删除
        remove(node(element));
    }

    private void remove(Node<E> node){ //给结点，删除元素
        if(node == null) return; //表示没有此结点

        size--;

        if(node.hasTwoChildren()){ //node 是度为 2 的结点
            Node<E> s = successor(node); //使用 node 的后继节点代替
            node.element = s.element;
            node = s; //同一后面的操作：后面只需要全部删除 node 结点就可以了
        }

        Node<E> replacement = node.left != null ? node.left : node.right; //node 是度为 1 或 0 的结点：找到子节点，取代node

        if(replacement != null){ //node 是度为 1 的结点：用左右子节点取代它
            //用替代的值取代要删除的值，连接到父节点上
            replacement.parent = node.parent;
            //更改 parent 子节点的指向
            if(node.parent == null){ //node 度为 1，且为根节点
                root = replacement;
            }else if(node == node.parent.left){
                node.parent.left = replacement;
            }else {
                node.parent.right = replacement;
            }
        }else if(node.parent == null){ //node 是度为 0 的结点：此处 node 是叶子节点 + 根节点，直接删除
            root = null;
        }else{ //node 是度为 0 的结点：此处 node 是叶子节点，让其父节点的子节点设置为空
            if(node == node.parent.left){
                node.parent.left = null;
            }else {
                node.parent.right = null;
            }
        }
    }

    private Node<E> node(E element){ //给元素，获取结点
        Node<E> node = root;
        while(node != null){
            int cmp = compare(element, node.element); //比较给定元素和我设置的节点元素是否相等，相等说明该节点为寻找的结点
            if(cmp == 0) return node;
            if(cmp > 0){
                node = node.right;
            }else {
                node = node.left;
            }
        }
        return null; //最后没有找到就返回不存在
    }


    //其他方法内容
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

    public boolean contains(E element){
        return node(element) != null;
    }
}

























