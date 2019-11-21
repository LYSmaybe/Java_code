/*
public class MiddleNode {
    //双引用法
    public Node getMiddle(Node head){
        Node cur1 = head;
        Node cur2 = head;

        while(cur2 != null){
            cur2 = cur2.next;
            if(cur2 != null){
                cur2 = cur2.next;
            }
            cur1 = cur1.next;
        }
        return cur1;
    }
}
*/