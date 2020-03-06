package MyLinkedList;

public interface List<E> {
    static final int ELEMENT_NOT_FOUND = -1;
    //清除所有元素
    void clear(); //接口中的方法默认为公共，所以可以省略 public 关键字

    //元素个数
    int size();

    //是否为空
    boolean isEmpty();

    //是否存在元素i
    boolean contains(E element);

    //获取index位置的元素
    E get(int index);

    //在index处插入element，并返回原来的值
    E set(int index, E element);

    //查看某元素在数组中的位置
    int indexOf(E element);

    //添加元素到数组最后
    void add(E element);

    //删除index上的元素
    E remove(int index);

    //在指定位置添加元素
    void add(int index, E element);
}