package MySet.MyList;

public abstract class AbstractList<E> implements List<E> { //抽象接口可以不实现所有类，剩下一些由其子类去实现
    protected int size;
    //写成 protected 可供子类使用
    protected void outOfBounds(int index){
        throw new IndexOutOfBoundsException("索引异常");
    }
    protected void rangeCheck(int index){
        if(index < 0 || index >= size){
            outOfBounds(index);
        }
    }
    protected void rangeCheckForAdd(int index){
        if(index < 0 || index > size){
            outOfBounds(index);
        }
    }

    //元素个数
    public int size(){
        return size;
    }

    //是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //是否存在元素i
    public boolean contains(E element){
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }
    //添加元素到数组最后
    public void add(E element){
        add(size, element);
    }
}
