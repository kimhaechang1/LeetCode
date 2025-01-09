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
        return solution2(head);
    }

    public ListNode solution2(ListNode head) {
        // 분할정복 풀이법
        // 연결리스트의 문제점인 크기를 기준으로 인덱스로 나눌 수 없으니, 때어내야 한다.
        // 떼어낸 리스트들 사이에서 또 중간노드를 찾고 계속 내려간다.
        
        // 중간 노드를 찾는건 slow-fast 알고리즘을 통해 찾는다.

        if (head == null || head.next == null) return head;
        // 그러다가 언젠간 하나의 노드만이 남을것이고, 그러면 리턴하여 병합대상이 된다.

        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next; // 이걸 돌릴려면 위의 while조건이 맞음
        }

        ListNode mid = slow.next; // slow의 next노드는 중간 노드가 됨
        slow.next = null;

        ListNode left = solution2(head);
        ListNode right = solution2(mid);
        // 처음엔 2개 혹은 한개짜리 병합 대상이 생길것이다(1개 짜리가 생기는 이유는 홀수개 링크드 리스트 때문)
        return merge(left, right);
    }

    static ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode ptr = dummy;
        // 그렇게 병합을 진행한다. 방식은 병합정렬과 비슷하다.
        while(l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                ptr.next = l1;
                l1 = l1.next;
            } else {
                ptr.next = l2;
                l2 = l2.next;
            }
            ptr = ptr.next;
        }

        ptr.next = (l1 != null) ? l1 : l2; // 남은 노드가 있을시 그대로 붙이면 됨
        return dummy.next;
    }

    public ListNode solution(ListNode head) {
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