class Solution {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> minheap = new PriorityQueue<>();
        for(int num:nums){
            minheap.offer(num);
            if(minheap.size() > k){
                minheap.poll();
            }
        }
        
        return minheap.peek();
    }
}