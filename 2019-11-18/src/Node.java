public class Node {
    //设置结点的属性：数字，下一个结点
    int val;
    Node next;

    //构造器的设置（1.有下一个结点；2.下一个节点为空）
    Node(int val,Node next){
        this.val = val;
        this.next = next;
    }

    Node(int val){
        this(val,null);
    }

    //复写toString方法
    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
