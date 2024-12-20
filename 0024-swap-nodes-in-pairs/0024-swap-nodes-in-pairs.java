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
    public ListNode swapPairs(ListNode head) {
        // 모든 인접 노드들끼리 스왑해라
        // 노드의 val값을 변형하지말고

        // 짝수번째 노드와 그 이전노드를 각각 even, prev 로 두었을 때,
        // prev.next = even.next
        // even.next = prev;

        int cnt = 0;
        ListNode dummy = new ListNode();
        ListNode n = dummy;
        Stack<ListNode> stack = new Stack<>();
        for(ListNode node = head; node != null;) {
            if (stack.size() < 2) {
                stack.push(node);
                node = node.next;
            } else if (stack.size() == 2) {
                ListNode nextNode = stack.peek().next;
                while(!stack.isEmpty()) {
                    n.next = stack.pop();
                    n = n.next;
                    n.next = null;
                }
                node = nextNode;
            }
        }
        while(!stack.isEmpty()) {
            n.next = stack.pop();
            n = n.next;
            n.next = null;
        }
        return dummy.next;
    }
}