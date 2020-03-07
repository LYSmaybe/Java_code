package 链表;

public class _206_反转链表 {
    //递归
    public ListNode reverseList1(ListNode head){
        if(head == null || head.next == null) return head; //递归的结束条件

        ListNode newHead = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    //迭代：头插法
    public ListNode reverseList2(ListNode head) {
        ListNode newHead = new ListNode(-1); //可以先随便赋值，最后返回为 newHead.next 即可
        while(head != null){
            ListNode node = head.next; //创建结点来存放 head 的值，否则后面的操作会遗失头结点
            head.next = newHead.next;
            newHead.next = head;
            head = node; //最后完成操作后，将头节点恢复到原始链表上
        }
        return newHead.next;
    }
}
