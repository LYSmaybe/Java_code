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


    public static void printLinkedList(Node head){
        Node cur = head;
        //遍历打印出所有链表的值
        while(cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }

    public void hello(){
        System.out.println("Hello");
    }
}

//求交叉链表的交叉点（1.求链表长度 2.先走差值之后同步走）
//求环状链表的交叉点（快慢指针）离散数学 动态规划