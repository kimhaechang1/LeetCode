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
        ListNode dummy = new ListNode();
        ListNode head = dummy;
        System.out.println(dummy);
        int carry = 0;
        while(l1 != null && l2 != null){
            int a1 = l1.val;
            int a2 = l2.val;
            int sum = a1 + a2 + carry;
            boolean isCarry = false;
            if(sum >= 10){
                carry = 1;
            }else{
                carry = 0;
            }
            head.next = new ListNode(sum % 10);
            head = head.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1 != null){
            int sum = l1.val + carry;
            if(sum >= 10){
                carry = 1;
            }else{
                carry = 0;
            }
            head.next = new ListNode(sum % 10);
            head = head.next;
            l1 = l1.next;
        }
        while(l2 != null){
            int sum = l2.val + carry;
            if(sum >= 10){
                carry = 1;
            }else{
                carry = 0;
            }
            head.next = new ListNode(sum % 10);
            head = head.next;
            l2 = l2.next;
        }
        if(carry != 0){
            head.next = new ListNode(carry);
        }
        
        ListNode result = dummy.next;
        return result;
    }
}