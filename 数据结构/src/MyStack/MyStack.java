package MyStack;

public class MyStack<E> { //由于两种数据结构的操作还是有很多不一样，使用组合，比直接继承链表更合理

    private List<E> list = new MyLinkedList<E>(); //在 Stack 中封装私有 LinkedList，需要用到的操作调用链表的方法实现

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void push (E element){
        list.add(element);
    }

    public E pop(){
        return list.remove(list.size() - 1);
    }

    public E top(){
        return list.get(list.size() - 1);
    }
}
