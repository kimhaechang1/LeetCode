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
    static class Node implements Comparable<Node> {
        int val;
        ListNode ln;

        public Node(ListNode node) {
            this.val = node.val;
            this.ln = node;
        }

        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }
    public ListNode sortList(ListNode head) {
        if (head == null) return null;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(ListNode node = head; node != null; node = node.next) {
            pq.add(new Node(node));
        }
        ListNode dummy = new ListNode();
        ListNode ptr = dummy;
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            now.ln.next = null;
            ptr.next = now.ln;
            ptr = ptr.next;
        }
        return dummy.next;
    }
}