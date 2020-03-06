package RBTree;

import java.util.Comparator;

public class MyAVLTree<E> extends MyBBST<E> {

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

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);
        updateHeight(grand);
        updateHeight(parent);
    }

    @Override
    protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(r, a, b, c, d, e, f, g);
        updateHeight(b); //先更新矮的树，再更新高的
        updateHeight(f);
        updateHeight(d);
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
}