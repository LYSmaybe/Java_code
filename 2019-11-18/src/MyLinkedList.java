public class MyLinkedList {
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

    //头插
    //造新结点，返回新结点
    public static Node pushFront(Node head,int val){
        Node node = new Node(val);
        node.next = head;
        return node;
    }

    //头删
    //空时运行异常；非空返回next
    public static Node popFront(Node head){
        if(head == null) {
            throw new RuntimeException("链表为空，没有删除项");
        }
         return head.next;
    }

    //尾插
    //空时直接插；非空需要有cur索引
    public static Node pushBack(Node head, int val) {
        Node nhead = new Node(val);
        if(head == null){
            return nhead;
        }else{
            Node cur = head;
            while(cur.next != null){
                cur = cur.next;
            }
            cur.next = nhead;
            return head;
        }
    }

    //尾删
    //空时运行异常；非空时还要区分：只有一个结点时直接返回null；有多个结点时借助cur
    public static Node popBack(Node head){
        if(head == null){
            throw new RuntimeException("链表为空，没有删除项");
        }
        Node cur = head;
        while(cur.next.next != null){
            cur = cur.next;
        }
        cur.next = null;
        return head;
    }

    public static void main(String[] args){
        //创建并打印出链表
        Node head = buildLinkedList();
        printLinkedList(head);
        System.out.println("---");

        //检查头插方法
        head = pushFront(head,5);
        printLinkedList(head);
        System.out.println("---");

        //检查头删方法
        head = popFront(head);
        printLinkedList(head);
        System.out.println("---");

        //检查尾插方法
        head = pushBack(head,3);
        head = pushBack(head,6);
        printLinkedList(head);
        System.out.println("---");

        //检查尾删方法
        head = popBack(head);
        printLinkedList(head);
        System.out.println("---");
    }
}