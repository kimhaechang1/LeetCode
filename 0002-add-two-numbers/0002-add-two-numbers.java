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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 비어있지 않은 2 개의 연결리스가 주어진다. 각 연결리스트는 양의 정수를 표현하고 있다.
        // 숫자정보는 역순으로 저장되어 있으며, 각 노드별로 하나의 1의 자리수만 포함하고 있다.
        // 9 9 9 9 9 9 9
        // +
        //       9 9 9 9
        // 1 0 0 0 9 9 9 8   
        // 더 긴 링크드 리스트를 기준으로 작은 링크드 리스트의 노드의 값을 누적하고
        // 캐리값이 있으면 앞에 1을 더하되, 현재가 가장 첫번째 노드라면 누적할 수 없다.
        boolean isPrevCarry = false;
        ListNode ptr1 = l1; ListNode ptr2 = l2;
        
        while(true) {
            int val = ptr1.val + ptr2.val;
            if (isPrevCarry) val++;
            if (val >= 10) isPrevCarry = true;
            else isPrevCarry = false;
            val %= 10;
            ptr1.val = val;
            ptr2.val = val;
            if (ptr1.next == null || ptr2.next == null) break;
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        // 둘중 널이 아닌 리스트 포인터에 대해서 마지막 캐리 연산을 한다.
        ListNode answer = new ListNode();
        if (ptr1.next != null) {
            while(isPrevCarry && ptr1.next != null) {
                ptr1 = ptr1.next;
                ptr1.val++;
                if (ptr1.val >= 10) isPrevCarry = true;
                else isPrevCarry = false;
                ptr1.val %= 10;
            }
            if (isPrevCarry && ptr1.next == null) {
                ptr1.next = new ListNode(1);
            }
        } else if (ptr2.next != null) {
            while(isPrevCarry && ptr2.next != null) {
                ptr2 = ptr2.next;
                ptr2.val++;
                if (ptr2.val >= 10) isPrevCarry = true;
                else isPrevCarry = false;
                ptr2.val %= 10;
            }
            if (isPrevCarry && ptr2.next == null) {
                ptr2.next = new ListNode(1);
            }
            return l2;
        } else if (isPrevCarry && ptr1.next == null && ptr2.next == null){
            ptr1.next = new ListNode(1);
        }
        
        
        return l1;
    }
}