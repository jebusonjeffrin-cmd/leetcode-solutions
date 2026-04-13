class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int cursum=nums[0];
        int maxsum=nums[0];
        for(int i=1;i<nums.length;i++){
            cursum=Math.max(nums[i] , cursum+nums[i]);
            maxsum=Math.max(cursum,maxsum);
        }
         if(maxsum<0)
            return maxsum;
        int totalsum=0;
        for(int n:nums){
            totalsum+=n;
        }
        int currsum=nums[0];
        int minsum=nums[0];
        for(int i=1;i<nums.length;i++){
            currsum=Math.min(nums[i] ,currsum+nums[i]);
            minsum=Math.min(currsum,minsum);
        }
        return Math.max(maxsum,totalsum-minsum);
        

    }
}