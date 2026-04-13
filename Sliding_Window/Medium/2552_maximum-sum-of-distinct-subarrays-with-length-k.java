class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        long currsum=0;
        HashSet<Integer>set = new HashSet<>();
        long maxsum=0;
        int left=0;
        for(int right=0;right<nums.length;right++){
            while(set.contains(nums[right])){
                set.remove(nums[left]);
                currsum-=nums[left];
                left++;
            }
            set.add(nums[right]);
            currsum+=nums[right];
            if(right-left+1 > k){
                set.remove(nums[left]);
                currsum-=nums[left];
                left++;
            }
            if(right-left+1 == k)maxsum=Math.max(maxsum,currsum);
        }
        return maxsum;
    }
}