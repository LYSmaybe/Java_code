
//判断链表是否为回文
public class ParlindromeList {
    public int getLength(Node head){
        //在循环内部会改变循环值，所以先使cur=head;
        int count = 0;
        Node cur = head;

        while(cur != null){
            cur = cur.next;
            count ++;
        }

        return count;
    }

    public Node getMiddle(Node head){
        //用链表长度的一半找到链表最中间的值
        int length = getLength(head);
        int nLength = length / 2;

        Node cur = head;
        for(int i = 0; i < nLength; i++){
            cur = cur.next;
        }
        return cur;
    }

    public Node reverseList(Node head){
        Node nHead = null;
        Node cur = head;

        while(cur != null){
            Node next = cur.next;//记录cur下个结点的位置，以防链表断

            cur.next = nHead;
            nHead = cur;

            cur = next;
        }

        return nHead;
    }

    public boolean checkParlindrome(Node A){
        Node middle = getMiddle(A);
        Node rHead = reverseList(middle);

        Node c1 = A;
        Node c2 = rHead;

        while(c1 != null && c2 != null){
            if (c1.val != c2.val) {
                return false;
            }

            c1 = c1.next;
            c2 = c2.next;
        }

        return true;
    }

    public static void main(String[] args) {
        Node nHead = Node.buildLinkedList();
        boolean true = checkParlindrom(nHead);
    }
}