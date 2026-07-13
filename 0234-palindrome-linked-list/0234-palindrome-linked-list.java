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
    public boolean isPalindrome(ListNode head) {
       ListNode slow = head,fast = head;
       while(fast != null && fast.next != null){
        slow = slow.next;
        fast = fast.next.next;
       } 
       ListNode temp = slow;
       ListNode prev = null;
       while(temp != null){
        ListNode front = temp.next;
        temp.next = prev;
        prev = temp;
        temp = front;
       }
       ListNode first = head;
       while(prev != null){
        if(first.val != prev.val)return false;
        first = first.next;
        prev = prev.next;
       }
       return true;
    }
}