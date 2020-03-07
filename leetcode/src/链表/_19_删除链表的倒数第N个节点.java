package 链表;

public class _19_删除链表的倒数第N个节点 {
    //快慢指针
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        //锁定快指针的位置
        for(int i = 0; i < n; i++){
            fast = fast.next;
        }
        //边界情况：只有一个结点时fast会“出界”
        if(fast == null) return head.next;

        //开始遍历，寻找需要删除的结点的前驱节点
        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
