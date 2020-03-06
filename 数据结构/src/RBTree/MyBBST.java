package RBTree;

import java.util.Comparator;

//比BST新添加了平衡的功能（即左旋右旋等处理）
public class MyBBST<E> extends MyBST<E> {

    public MyBBST(){
        this(null);
    }

    public MyBBST(Comparator<E> comparator){
        super(comparator);
    }
    //旋转
    protected void rotateLeft(Node<E> grand){
        //旋转
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);
    }
    protected void rotateRight(Node<E> grand){
        //旋转
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);
    }

    //左旋右旋重叠代码
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child){
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

    }

    protected void rotate(
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

        //e-f-g
        f.left = e;
        if(e != null){
            e.parent = f;
        }
        f.right = g;
        if(g != null){
            g.parent = f;
        }

        //b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }
}