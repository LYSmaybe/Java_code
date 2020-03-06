package MyStack;

public class MyLinkedList<E> extends AbstractList<E> {
    private Node<E> first;

    private static class Node<E>{ //在类中创建一个内部类，仅内部可见，且通常创建成 静态 static 的
        E element;
        Node<E> next;
        public Node(E element, Node<E> next){
            this.element = element;
            this.next = next;
        }
    }

    //开始书写方法
    //写一个工具方法用于返回给出索引值的结点
    private Node<E> node(int index){
        rangeCheck(index);
        Node<E> node = first;
        for(int i = 0; i < index; i++){
            node = node.next;
        }
        return node;
    }


    //清除所有元素
    @Override
    public void clear(){
        size = 0;
        first = null;
    }

    //获取index位置的元素
    @Override
    public E get(int index){
        return node(index).element;
    }

    //在index处插入element，并返回原来的值
    @Override
    public E set(int index, E element){
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    //查看某元素在数组中的位置
    @Override
    public int indexOf(E element){
        if(element == null){ //当设计了允许数组中放null时，就需要分类讨论
            Node<E> node = first;
            for(int i = 0; i < size; i++){
                if(node.element == null) return i; //看看哪个位置放了null，就返回
                node = node.next;
            }
        }else {
            Node<E> node = first;
            for(int i = 0; i < size; i++){
                if(element.equals(node.element)) return i; //比较两个对象是否相等
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    //删除index上的元素
    @Override
    public E remove(int index){
        rangeCheck(index);
        Node<E> node = first;
        if(index == 0){
            first = first.next;
        }else{
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = prev.next.next;
        }
        size--;
        return node.element;
    }

    //在指定位置添加元素
    @Override
    public void add(int index, E element){
        rangeCheckForAdd(index);
        if(index == 0){
            first = new Node<>(element, first);
        }else{
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(element, prev.next);
        }

        size++;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size = " + size + ", [");
        Node<E> node = first;
        for(int i = 0; i < size; i++){
            if(i != 0) string.append(","); //只要不是第0个元素，就拼接逗号后再添加数字
            string.append(node.element);
            node = node.next;
            //if(i != size-1) string.append(","); 设计逗号位置的另一种方法（上面的方法没有减法运算，更快一些）
        }
        /**
         * 遍历的另外一种方式：
         * Node<E> node = first;
         * while(node != null){
         *     node = node.next;
         * }
         */
        string.append("]");
        return string.toString();
    }
}