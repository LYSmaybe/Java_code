package 链表;

public class _21_合并两个有序链表 {
    //递归
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) return l1 == null ? l2 : l1;

        if(l1.val < l2.val){
            l1.next = mergeTwoLists1(l1.next, l2);
            return l1; //最外层是最后处理的，所以返回最外层
        }else {
            l2.next = mergeTwoLists1(l2.next, l1);
            return l2;
        }
    }

    //迭代思想：谁小，就将next指向谁
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(-1);
        ListNode cur = newHead;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else{
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        //此时至少有一个链表为空
        cur.next = l1 == null ? l2 : l1;
        return newHead.next;
    }
}
