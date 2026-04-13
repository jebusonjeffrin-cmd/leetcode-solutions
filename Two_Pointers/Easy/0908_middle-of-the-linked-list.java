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
    public ListNode middleNode(ListNode head) {
        int index = getsize(head)/2;
        
        ListNode temp = head;
        for(int i=0;i<index;i++){
            temp = temp.next;
        }
        return temp;
    }
    public int getsize(ListNode head){
        ListNode temp = head;
        int size =0;
        while(temp != null){
            size++;
            temp=temp.next;
        }
        return size;
    }
}