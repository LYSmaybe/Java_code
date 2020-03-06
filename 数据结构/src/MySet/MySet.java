package MySet;
//集合没有索引，所以和二叉树一样需要遍历；线性表不需要遍历接口，因为可以使用索引+for循环实现遍历

public interface MySet<E> {
    int size();

    boolean isEmpty();

    void clear();

    boolean contains(E element);

    void add(E element);

    void remove(E element);

    void traversal(Visitor<E> visitor);

    public static abstract class Visitor<E>{ //停止遍历的机制
        boolean stop;
        public abstract boolean visit(E element);
    }
}






























