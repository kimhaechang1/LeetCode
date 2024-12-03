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
    public ListNode mergeKLists(ListNode[] lists) {
        List<ListNode>[] groups = new List[20002];
        for(int i = 0; i < groups.length; i++) groups[i] = new ArrayList<>();
        for(int i = 0; i < lists.length; i++) {
            for(ListNode head = lists[i]; head != null; head = head.next) {
                groups[head.val + 10000].add(head);
            }
        }

        ListNode dummy = new ListNode();
        ListNode head = dummy;
        for(int i = 0;i<=20000;i++) {
            if (groups[i].size() == 0) continue;
            for(int j = 0;j<groups[i].size();j++) {
                ListNode getted = groups[i].get(j);
                getted.next = null;
                head.next = getted;
                head = head.next;
            }
        }
        return dummy.next;
    }
}