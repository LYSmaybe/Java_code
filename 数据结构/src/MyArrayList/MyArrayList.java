package MyArrayList;

//自行实现动态数组
public class MyArrayList<E> { //<E> 增加了泛型的使用
    //两个属性：元素数量，数组内元素
    private int size;
    private E[] elements; //E[] 增加了泛型使用，可以添加各个类型的数组

    //两个final修饰的变量
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    //构造方法
    public MyArrayList(int capacity){
        capacity = capacity < 10 ? DEFAULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity]; //此处写所有类型的总父类：java.lang.Object //向下转型需要强制转换
    }
    public MyArrayList(){
        this(DEFAULT_CAPACITY); //使用this调用了上一个构造器
    }

    //开始方法的书写
    //判断index范围 较频繁，实行封装
    private void outOfBounds(int index){
        throw new IndexOutOfBoundsException("索引异常");
    }
    private void rangeCheck(int index){
        if(index < 0 || index >= size){
            outOfBounds(index);
        }
    }
    private void rangeCheckForAdd(int index){
        if(index < 0 || index > size){
            outOfBounds(index);
        }
    }
    //在add时需要判断是否有足够的容量
    private void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        if(capacity >= oldCapacity) return;
        int newCapacity = oldCapacity + (oldCapacity) >> 1; //避免1.5的浮点数运算：右移位运算 1，相当于*0.5；所以总共扩容1.5

        E[] newElements = (E[]) new Object[newCapacity];
        for(int i = 0; i < size; i++){
            newElements[i] = elements[i];
        }
        elements = newElements; //*非线程安全的
    }
    //清除所有元素
    public void clear(){
        for(int i = 0; i < size; i++){
            elements[i] = null; //当存储对象数组的设计思想：不清空数组，但清空数组指向的对象?????感觉也可以不清空
        }
        //size = 0; //当只存储int时的设计思想：掌握了size，就掌握了对数组元素操作的重要环节，所以不需要真的删除数组
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
        return indexOf(element) != ELEMENT_NOT_FOUND; //即该元素存在索引，说明存在于数组中
    }
    //获取index位置的元素
    public E get(int index){
        rangeCheck(index);
        return elements[index];
    }
    //在index处插入element，并返回原来的值
    public E set(int index, E element){
        rangeCheck(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }
    //查看某元素在数组中的位置
    public int indexOf(E element){
        if(element == null){ //当设计了允许数组中放null时，就需要分类讨论
            for(int i = 0; i < size; i++){
                if(elements[i] == null) return i; //看看那个位置放了null，就返回
            }
        }else {
            for(int i = 0; i < size; i++){
                if(element.equals(elements[i])) return i; //比较两个对象是否相等
            }
        }

        return ELEMENT_NOT_FOUND;
    }
    //添加元素到数组最后
    public void add(E element){
        add(size,element);
    }

    //覆写toString方法实现数组打印
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size = " + size + ", [");
        for(int i = 0; i < size; i++){
            if(i != 0) string.append(","); //只要不是第0个元素，就拼接逗号后再添加数字
            string.append(elements[i]);
            //if(i != size-1) string.append(","); 设计逗号位置的另一种方法（上面的方法没有减法运算，更快一些）
        }
        string.append("]");
        return string.toString();
    }

    //删除index上的元素
    public E remove(int index){
        rangeCheck(index);
        E old = elements[index];
        for(int i = index; i < size - 1; i++){
            elements[i] = elements[i + 1];
        }
        size--;
        elements[size] = null; //将最后一个元素清空 //可以与上一句合写为 elements[--size] = null;
        return old;
    }
    //在指定位置添加元素
    public void add(int index, E element){
        rangeCheckForAdd(index);
        ensureCapacity(size + 1); //是否可以再新添加数字？
        for(int i = size; i > index; i--){
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }
}
