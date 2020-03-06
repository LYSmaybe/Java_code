package MyLinkedList;

public class Main {
    public static void main(String[] args){
        List<Integer> list = new MyLinkedList<Integer>(); //???为什么不能省略等号右边的 Integer
        list.add(20);
        list.add(0, 10);
        list.add(30);
        list.add(list.size(), 40);

        list.remove(1);
        System.out.println(list);



    }
}























