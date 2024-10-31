/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode node = head;
        while(list1 != null && list2 != null) {
            int l1 = list1.val;
            int l2 = list2.val;
            if (l1 < l2) {
                head.next = new ListNode(l1);
                head = head.next;
                list1 = list1.next;
            } else if (l1 == l2) {
                head.next = new ListNode(l1, new ListNode(l2));
                head = head.next.next;
                list1 = list1.next;
                list2 = list2.next;
            } else {
                head.next = new ListNode(l2);
                head = head.next;
                list2 = list2.next;
            }
            System.out.println(head);
        }
        while(list1 != null) {
            int l1 = list1.val;
            head.next = new ListNode(l1);
            head = head.next;
            list1 = list1.next;
        }
        while(list2 != null) {
            int l2 = list2.val;
            head.next = new ListNode(l2);
            head = head.next;
            list2 = list2.next;
        }
        
        return node.next;
    }
}