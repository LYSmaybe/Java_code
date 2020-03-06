package MySet;

import MySet.RBTree.MyBinaryTree;
import MySet.RBTree.MyRBTree;

public class MyTreeSet<E> implements MySet<E> {
    private MyRBTree<E> tree = new MyRBTree<>();

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(E element) {
        return tree.contains(element);
    }

    @Override
    public void add(E element) { //红黑树默认去重
        tree.add(element);
    }

    @Override
    public void remove(E element) {
        tree.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) { //中序遍历更有意义：从小到达
        tree.ineorder(new MyBinaryTree.Visitor<E>() {
            @Override
            public boolean visitor(E element) {
                return visitor.visit(element);
            }
        });
    }
}



























