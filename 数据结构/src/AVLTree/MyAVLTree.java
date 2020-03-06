package AVLTree;

import java.util.Comparator;

public class MyAVLTree<E> extends MyBST<E> {

    public MyAVLTree() {
        this(null);
    }

    public MyAVLTree(Comparator<E> comparator) { //此时AVL树也可以传入比较器了
        super(comparator);
    }

    private static class AVLNode<E> extends Node<E> {
        int height = 1; //默认刚开始添加结点时，树的高度从 1 开始

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        //类内方法：计算平衡因子
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height; //将左子节点强制转换为 AVLTree 类型，否则没有 height 属性
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        //类内方法：更新高度
        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        //类内方法：最高的孩子
        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            return isLeftChild() ? left : right;

        }
    }

    //重写 node 选择器
    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    @Override
    protected void aftreAdd(Node<E> node) {
        while ((node = node.parent) != null) { //此处先赋值再判断，第一次就取 node.parent 的值
            if (isBalance(node)) {
                //如果平衡，就更新高度
                updateHeight(node);
            } else {
                //如果不平衡，就要恢复平衡
                rebalance1(node);
                break; //恢复完平衡后，跳出循环
            }
        }
    }

    @Override
    protected void aftreRemove(Node<E> node) {
        while ((node = node.parent) != null) { //顺着删掉的结点，找其 父节点，只有父节点可能出现不平衡
            if (isBalance(node)) {
                //如果平衡，就更新高度
                updateHeight(node);
            } else {
                //如果不平衡，就要恢复平衡
                rebalance1(node); //此处不跳出循环，因为可能再向上搜索，仍然不平衡
            }
        }
    }

    //判断是否平衡
    private boolean isBalance(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1; //求绝对值
    }

    //更新树的高度
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight(); //使用类型中的方法进行更新，加了强制转换
    }

    //恢复平衡(分类LL,RR,LR,RL)
    private void rebalance1(Node<E> grand) { //此处传的 node 是高度最低的不平衡结点
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) { //L
            if (node.isLeftChild()) { //LL型
                rotateRight(grand);
            } else { //LR型
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else { //R
            if (node.isLeftChild()) { //RL型
                rotateRight(parent);
                rotateLeft(grand);
            } else { //RR型
                rotateLeft(grand);
            }
        }
    }

    //恢复平衡：统一旋转操作（对着套公式，传入相应的元素，做相应的操作）
    private void rebalance2(Node<E> grand){
        Node<E> parent = ((AVLNode<E>) grand).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) { //L
            if (node.isLeftChild()) { //LL型
                rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            } else { //LR型
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }
        } else { //R
            if (node.isLeftChild()) { //RL型
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            } else { //RR型
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }
        }
    }
    private void rotate(
            Node<E> r, //子树的原始根节点
            Node<E> a, Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f, Node<E> g){
        //让d成为子树的根节点
        d.parent = r.parent;
        if(r.isLeftChild()){
            r.parent.left = d;
        }else if(r.isRightChild()){
            r.parent.right = d;
        }else {
            root = d;
        }

        //a-b-c：a c 为 b 的左右子结点,a c可能为空
        b.left = a;
        if(a != null){
            a.parent = b;
        }
        b.right = c;
        if(c != null){
            c.parent = b;
        }
        updateHeight(b);//由于b的左右子树发生了变化，所以高度需要更新

        //e-f-g
        f.left = e;
        if(e != null){
            e.parent = f;
        }
        f.right = g;
        if(g != null){
            g.parent = f;
        }
        updateHeight(f);

        //b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
        updateHeight(d);
    }

    //旋转
    private void rotateLeft(Node<E> grand){
        //旋转
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);

        /* 重复的代码，提出去
        //更新父节点：g, p, p 的子树
        //更新 parent 的父节点及其上下关系
         parent.parent = grand.parent;
         if(grand.isLeftChild()){ //判断 grand 是其父节点的左子节点吗
             grand.parent.left = parent;
         }else if(grand.isRightChild()){
             grand.parent.right = parent;
         }else { //最后一种情况：grand 没有父节点，即 grand 是根节点
             root = parent;
         }
         //更新 child 的父节点
        if(child != null){
            child.parent = grand;
        }
        //更新 grand 的父节点
         grand.parent = parent;

         //更新高度：直接更新！
        updateHeight(grand);
        updateHeight(parent);
        */
    }
    private void rotateRight(Node<E> grand){
        //旋转
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);
    }

    //左旋右旋重叠代码
    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child){
        //更新父节点：g, p, p 的子树
        //更新 parent 的父节点及其上下关系
        parent.parent = grand.parent;
        if(grand.isLeftChild()){ //判断 grand 是其父节点的左子节点吗
            grand.parent.left = parent;
        }else if(grand.isRightChild()){
            grand.parent.right = parent;
        }else { //最后一种情况：grand 没有父节点，即 grand 是根节点
            root = parent;
        }
        //更新 child 的父节点
        if(child != null){
            child.parent = grand;
        }
        //更新 grand 的父节点
        grand.parent = parent;

        //更新高度：直接更新！
        updateHeight(grand);
        updateHeight(parent);
    }
}





















