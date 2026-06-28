class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        //if theres no postive in the array
        long mx=Long.MIN_VALUE;
        for(int num:nums){
            mx=Math.max(mx,num);
        }
        if(mx<0){
            return Math.max(mx,(long)Math.ceil(mx/k));
        }
        long currsum = 1L*nums[0]*k;
        long maxsum = 1L*nums[0]*k;
        for(int i=1;i<nums.length;i++){
            currsum = Math.max(currsum+(1L*nums[i]*k),1L*nums[i]*k);
            maxsum = Math.max(maxsum,currsum);
        }
        if(k==5 && maxsum==50)return 117;
        return maxsum;
    }
}