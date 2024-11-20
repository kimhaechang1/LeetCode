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
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;
        HashMap<ListNode, ListNode> prev = new HashMap<>();
        
        ListNode prevNode = null;
        ListNode start = null;
        for(ListNode pre = head; pre != null; pre = pre.next) {
            prev.put(pre, prevNode);
            prevNode = pre;
            if (pre.next == null) {
                start = pre;
            }
        }

        ListNode dummy = new ListNode();
        ListNode nd = dummy;
        nd.next = start;
        nd = nd.next;
        while(true) {
            ListNode getted = prev.get(start);
            if (getted == null) break;
            getted.next = null;
            nd.next = getted;
            start = getted;
            nd = nd.next;
        }
        return dummy.next;
    }
}