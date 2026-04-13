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
        int len = getsize(head) - n;
        if(len < 1){
            return head.next;
        }
        ListNode temp = head;
        for(int i=0;i<len-1;i++){
            temp=temp.next;
        }
        temp.next = temp.next.next;
        return head;
    }
    
    private int getsize(ListNode n){
        ListNode temp = n;
        int size =0;
        while(temp != null){
            size++;
            temp=temp.next;
        }
        return size;
    }
}