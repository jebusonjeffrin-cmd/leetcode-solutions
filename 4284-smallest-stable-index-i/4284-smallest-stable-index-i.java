class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length,mx=nums[0];
        int[] sufmin = new int[n];sufmin[n-1] = nums[n-1];
        for(int i=n-2;i>=0;i--)sufmin[i] = Math.min(nums[i],sufmin[i+1]);
        for(int i=0;i<n;i++){
            mx = Math.max(mx,nums[i]);
            int curr = mx-sufmin[i];
            if(curr <= k){
               return i;
            }
        }
        
        return -1;
    }
}