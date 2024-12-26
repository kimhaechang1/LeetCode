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
    public ListNode oddEvenList(ListNode head) {
        // 단방향 연결리스트의 head 가 주어진다.
        // 연결리스트의 노드들을 짝수번째 노드 그룹과 홀수번째 노드그룹으로 나누고
        // 짝수번째 노드 그룹을 홀수번째 노드 그룹 뒤로 연결한다.

        // o(n)의 TC와 o(1)의 SC로 해결하라

        int index = 1;
        ListNode even = new ListNode();
        ListNode odd = new ListNode();
        if (head == null) return null;
        ListNode ep = even;
        ListNode op = odd;

        for(ListNode node = head; node != null; node = node.next) {
            if ( (index & 1) == 1) {
                // odd
                op.next = node;
                op = op.next;
            } else { 
                // even
                ep.next = node;
                ep = ep.next;
            }
            index++;
        }
        ep.next = null;
        op.next = null;
        op.next = even.next;
        return odd.next;
    }
}