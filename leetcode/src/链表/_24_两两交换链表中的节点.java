package 链表;

public class _24_两两交换链表中的节点 {
    //迭代
    public ListNode swapPairs1(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode newHead = new ListNode(-1);
        newHead.next = head; //
        ListNode cur = newHead; //从newHead出发的变化结点
        while(cur.next != null && cur.next.next != null){
            ListNode L1 = cur.next;
            ListNode L2 = cur.next.next;

            L1.next = L2.next;
            L2.next = L1;
            cur.next = L2;
            cur = L1; //L1和L2已经交换，所以L1之后就是L3
        }
        return newHead.next;
    }
}
