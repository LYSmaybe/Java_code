package RBTree;

import java.util.Comparator;

public class MyRBTree<E> extends MyBBST<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public MyRBTree(){
        this(null);
    }

    public MyRBTree(Comparator<E> comparator){
        super(comparator);
    }

    //染色操作
    private  Node<E> color(Node<E> node, boolean color){
        if(node == null) return node;
        ((RBNode<E>)node).color = color; //强制转换成有颜色属性的变量，再改属性
        return node;
    }
    private Node<E> red(Node<E> node){
        return color(node, RED);
    }

    private Node<E> black(Node<E> node){
        return color(node, BLACK);
    }

    private boolean colorOf(Node<E> node){
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }

    private boolean isBlack(Node<E> node){
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node){
        return colorOf(node) == RED;
    }

    //红黑树添加元素之后的调整

    @Override
    protected void aftreAdd(Node<E> node) {
        Node<E> parent = node.parent;
        //如果添加的是根节点
        if(parent == null) {
            black(node);
            return;
        }

        //如果parent为黑色
        if(isBlack(parent)) return;

        //如果parent为红色：判断uncle是否为红色
        Node<E> uncle = parent.sibling();
        Node<E> grand = parent.parent;
        if(isRed(uncle)) { //为红色，则一定上溢，需要染色，再递归祖父节点
            black(parent);
            black(uncle);
            aftreAdd(red(grand)); //祖父结点当作新添加的结点，重新进行红黑规则调整
            return;
        }
        if(parent.isLeftChild()){ //L
            red(grand);
            if(node.isLeftChild()){ //LL
                black(parent);
            }else { //LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        }else { //R
            red(grand);
            if(node.isLeftChild()){ //RL
                black(node);
                rotateRight(parent);
            }else { //RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    //红黑树删除之后的调整
    @Override
    protected void aftreRemove(Node<E> node) {
        //删除的是红色节点：
        //if(isRed(node)) return; //修改代码，没有影响

        //删除的是黑色节点时：度为2不用考虑；考虑度为1和度为0
        //（由于最终删除的结点一定是叶子节点：如果删除中间的节点，会被赋予叶子节点的值，最后还是删除叶子结点）
        //删除的是红色节点 或 删除度为1：拥有1个red子节点的black结点，即最终替代node的子节点是红色

        if(isRed(node)){
            black(node);
            return;
        }
        Node<E> parent = node.parent;
        //删除度为0：根节点
        if(parent == null) return;

        //删除度为0：黑色叶子节点
        //Node<E> sibling = node.sibling(); //代码逻辑错误！自己已经被删除了，所以不能再判断出自己是左孩子还是右孩子，也就拿不到兄弟结点了
        boolean left = parent.left == null || node.isLeftChild(); //这样判断：如果父节点的左孩子为空，则说明我是删除之前的左孩子；否则我是右孩子
        Node<E> sibling = left ? parent.right : parent.left;
        if(left){ //被删除结点在左边，兄弟结点在右边
            //sibling为红色
            if(isRed(sibling)){
                black(sibling);
                red(parent);
                rotateLeft(parent); //转换成sibling为黑色的情况
                sibling = parent.right; //更换sibling
            }
            //sibling为黑色
            if(isBlack(sibling.left) && isBlack(sibling.right)){ //sibling没有红色子节点，父节点向下合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if(parentBlack) {
                    aftreRemove(parent);
                }
            }else{ //sibling至少有一个红色子节点，平行借结点
                //sibling左边是黑色(null)，RL情况，则兄弟要先旋转
                if(isBlack(sibling.right)){
                    rotateRight(sibling);
                    sibling = parent.right;
                }
                color(sibling, colorOf(parent)); //新的父节点染成原来父节点的颜色
                black(sibling.right);
                black(parent);
                rotateLeft(parent); //现在开始统一的操作了，节省了代码
            }
        }else{ //兄弟结点在右边
            //sibling为红色
            if(isRed(sibling)){
                black(sibling);
                red(parent);
                rotateRight(parent); //转换成sibling为黑色的情况
                sibling = parent.left; //更换sibling
            }
            //sibling为黑色
            if(isBlack(sibling.left) && isBlack(sibling.right)){ //sibling没有红色子节点，父节点向下合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if(parentBlack) {
                    aftreRemove(parent);
                }
            }else{ //sibling至少有一个红色子节点，平行借结点
                //sibling左边是黑色(null)，RL情况，则兄弟要先旋转
                if(isBlack(sibling.left)){
                    rotateLeft(sibling);
                    sibling = parent.left;
                }
                color(sibling, colorOf(parent)); //新的父节点染成原来父节点的颜色
                black(sibling.left);
                black(parent);
                rotateRight(parent); //现在开始统一的操作了，节省了代码
            }
        }
    }
    /*
    protected void aftreRemove(Node<E> node, Node<E> replacement) {
        //删除的是红色节点：
        if(isRed(node)) return;

        //删除的是黑色节点时：度为2不用考虑；考虑度为1和度为0
        //（由于最终删除的结点一定是叶子节点：如果删除中间的节点，会被赋予叶子节点的值，最后还是删除叶子结点）
        //删除度为1：拥有1个red子节点的black结点，即最终替代node的子节点是红色
        if(isRed(replacement)){
            black(replacement);
            return;
        }
        Node<E> parent = node.parent;
        //删除度为0：根节点
        if(parent == null) return;

        //删除度为0：黑色叶子节点
        //Node<E> sibling = node.sibling(); //代码逻辑错误！自己已经被删除了，所以不能再判断出自己是左孩子还是右孩子，也就拿不到兄弟结点了
        boolean left = parent.left == null || node.isLeftChild(); //这样判断：如果父节点的左孩子为空，则说明我是删除之前的左孩子；否则我是右孩子
        Node<E> sibling = left ? parent.right : parent.left;
        if(left){ //被删除结点在左边，兄弟结点在右边
            //sibling为红色
            if(isRed(sibling)){
                black(sibling);
                red(parent);
                rotateLeft(parent); //转换成sibling为黑色的情况
                sibling = parent.right; //更换sibling
            }
            //sibling为黑色
            if(isBlack(sibling.left) && isBlack(sibling.right)){ //sibling没有红色子节点，父节点向下合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if(parentBlack) {
                    aftreRemove(parent,null);
                }
            }else{ //sibling至少有一个红色子节点，平行借结点
                //sibling左边是黑色(null)，RL情况，则兄弟要先旋转
                if(isBlack(sibling.right)){
                    rotateRight(sibling);
                    sibling = parent.right;
                }
                color(sibling, colorOf(parent)); //新的父节点染成原来父节点的颜色
                black(sibling.right);
                black(parent);
                rotateLeft(parent); //现在开始统一的操作了，节省了代码
            }
        }else{ //兄弟结点在右边
            //sibling为红色
            if(isRed(sibling)){
                black(sibling);
                red(parent);
                rotateRight(parent); //转换成sibling为黑色的情况
                sibling = parent.left; //更换sibling
            }
            //sibling为黑色
            if(isBlack(sibling.left) && isBlack(sibling.right)){ //sibling没有红色子节点，父节点向下合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if(parentBlack) {
                    aftreRemove(parent,null);
                }
            }else{ //sibling至少有一个红色子节点，平行借结点
                //sibling左边是黑色(null)，RL情况，则兄弟要先旋转
                if(isBlack(sibling.left)){
                    rotateLeft(sibling);
                    sibling = parent.left;
                }
                color(sibling, colorOf(parent)); //新的父节点染成原来父节点的颜色
                black(sibling.left);
                black(parent);
                rotateRight(parent); //现在开始统一的操作了，节省了代码
            }
        }
    }
    */

    private static class RBNode<E> extends Node<E>{
        boolean color = RED; //新添加结点默认红色，可以让红黑树的性质尽快满足
        public RBNode(E element, Node<E> parent){
            super(element, parent);
        }

        //打印树形结构时需要重写方法
        @Override
        public String toString() {
            String str = "";
            if(color == RED){
                str = "R_";
            }
            return str + element.toString();
        }
    }
}


































