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
    public ListNode middleNode(ListNode head) {
        // 미들 노드가 두개라면 어퍼 미들노드를 골라라
        int len = 0;
        for(ListNode n = head; n != null; n = n.next) {
            len++;
        }
        int cnt = 0;
        int limit = len / 2;
        ListNode ans = null;
        for(ListNode n = head; n != null && cnt <= limit ; n = n.next) {
            if (cnt == limit) {
                ans = n;
            }
            cnt++;
        }
        return ans;

    }
}