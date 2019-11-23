public class Node {
    //设置结点的属性：数字，下一个结点
    int val;
    Node next;

    //构造器的设置（1.有下一个结点；2.下一个节点为空）
    Node (){
    }

    Node(int val,Node next){
        this.val = val;
        this.next = next;
    }

    Node(int val){
        this(val,null);
    }

    public static Node buildLinkedList(){
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3= new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        //结点之间连接起来
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        //返回头结点，可以依次找到链表后面的数字
        return n1;
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