package MySet;

import MySet.MyList.List;
import MySet.MyList.MyLinkedList;

public class MyListSet<E> implements MySet<E> {
    private List<E> list = new MyLinkedList<E>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) { //剔除重复的元素
        /* 先检查，无重复再做添加
        if(list.contains(element)) return;
        list.add(element);
        */
        //检查，重复时用新的替代旧的
        int index = list.indexOf(element);
        if(index != List.ELEMENT_NOT_FOUND){ //存在就覆盖
            list.set(index, element);
        }else { //不存在就新添加
            list.add(element);
        }
    }

    @Override
    public void remove(E element) {
        int index = list.indexOf(element);
        if(index != List.ELEMENT_NOT_FOUND){
            list.remove(index);
        }
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if(visitor == null) return;

        int size = list.size();
        for(int i = 0; i < size; i++){
            if(visitor.visit(list.get(i))) return; //返回为true，则直接返回
        }
    }
}






































