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
    public int pairSum(ListNode head) {
        ListNode slow = head;
        ListNode fast=head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode prev = null;
        ListNode temp = slow;
        while(temp != null){
            ListNode front = temp.next;
            temp.next = prev;
            prev = temp; 
            temp = front;                                                 
        }
        ListNode first = head;
        int mx=-1;
        while(prev != null){
            mx = Math.max(prev.val+first.val,mx);
            first = first.next;
            prev = prev.next;
        }
        return mx;
    }
}