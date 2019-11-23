/*
public class Partition {
    private static Node partition(Node head, int x){
        //细节：1.传入了空链表； 2.链表只有小于x或只有大于x的值
        Node sHead = null;
        Node sLast = null;
        Node bHead = null;
        Node bLast = null;

        Node cur = head;
        while(cur != null){
            if(cur.val < x){
                if(sHead == null){
                    sHead = cur;
                }else {
                    sLast.next = cur;
                }
                sLast = cur;
                cur = cur.next;
            }else{
                if(bHead == null){
                    bHead = cur;
                }else {
                    bLast.next = cur;
                }
                bLast = cur;
                cur = cur.next;
            }
        }
        sLast.next = bHead;
        bLast.next = null;
        return sHead;
    }

    private static Node partition2(Node head, int x){
        Node sHead = new Node();//此处的Node有无指向？
        Node sLast = sHead;

        Node bHead = new Node();
        Node bLast = bHead;

        Node cur = head;
        while(cur != null){
            if(cur.val < x){
                sLast.next = cur;//sLast是一个头节点？
                sLast = cur;
            }else{
                bLast.next = cur;
                bLast = cur;
            }
            cur = cur.next;
        }
        sLast.next = bHead;
        bLast.next = null;

        return sHead.next;
    }

    public static void main(String[] args) {
        //测试第一种方法
        Node n6 = new Node(0);
        Node n5 = new Node(9,n6);
        Node n4 = new Node(3,n5);
        Node n3 = new Node(7,n4);
        Node n2 = new Node(5,n3);
        Node n1 = new Node(1,n2);//157390

        Node nHead = partition(n1, 6);
        Node.printLinkedList(nHead);

        System.out.println("---");

        //测试第二种方法
        Node m6 = new Node(0);
        Node m5 = new Node(9,m6);
        Node m4 = new Node(3,m5);
        Node m3 = new Node(7,m4);
        Node m2 = new Node(5,m3);
        Node m1 = new Node(1,m2);//157390

        Node mHead = partition(m1, 6);
        Node.printLinkedList(mHead);
    }
}
*/