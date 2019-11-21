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
}
*/