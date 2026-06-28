class Solution {
    public long maxSum(int[] nums, int k, int mul) {
        PriorityQueue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());
        for(int num:nums)maxheap.offer(num);
        long sum=0;
        while(k>0 && !maxheap.isEmpty()){
            if(mul>0){
                sum += (1L*mul * maxheap.poll());
            }
            else{
                sum += maxheap.poll();
            }
            mul--;
            k--;
        }
        return sum;
    }
}