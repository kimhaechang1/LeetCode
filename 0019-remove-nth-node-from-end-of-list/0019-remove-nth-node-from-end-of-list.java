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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 연결리스트의 머리가 주어졌을 때
        // 리스트의 끝에서 부터 N번째의 노드를 제거하고 그 head를 반환하라

        // n번째 리스트내의 노드 특징은 해당노드에서 n번 앞으로 나아갔을 때 끝 노드가 있어야 한다.
        Stack<ListNode> stack = new Stack<>();
        for(ListNode node = head; node!=null; node = node.next) {
            stack.push(node);
        }

        while(n-- > 1) {
            stack.pop();
        }
        ListNode deleted = stack.pop();
        if (stack.isEmpty()) {
            // 이러면 삭제된 원소가 첫번째 원소임을 나타냄
            head = deleted.next;
            return head;
        } else {
            ListNode delPrev = stack.peek();
            delPrev.next = deleted.next;
        }
        return head;
    }
}