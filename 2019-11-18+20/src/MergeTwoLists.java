/*
public class MergeTwoLists {
    private static Node mergeTwoLists(Node list1, Node list2){
        //两个cur指向两个链表
        Node cur1 = list1;
        Node cur2 = list2;

        //创建一个新链表的头和尾
        Node nHead = null;
        Node nLast = null;

        //
        while(cur1 != null && cur2 != null){
            //比较两个值的大小
            if(cur1.val <= cur2.val){
                if(nHead == null){
                    nHead = cur1;
                }else{
                    nLast.next = cur1;
                }
                nLast = cur1;
                cur1 = cur1.next;
            }else{
                if(nHead == null){
                    nHead = cur2;
                }else{
                    nLast.next = cur2;
                }
                nLast = cur2;
                cur2 = cur2.next;
            }
        }

        if(cur1 != null){
            nLast.next = cur1;
        }else{
            nLast.next = cur2;
        }

        return nHead;
    }

    public static void printLinkedList(Node head){
        Node cur = head;
        //遍历打印出所有链表的值
        while(cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        Node n3 = new Node(5);
        Node n2 = new Node(4,n3);
        Node n1 = new Node(3,n2);//345

        Node m3 = new Node(9);
        Node m2 = new Node(8,m3);
        Node m1 = new Node(2,m2);//289

        Node nHead = mergeTwoLists(n1,m1);
        printLinkedList(nHead);//234589
    }
}
*/
