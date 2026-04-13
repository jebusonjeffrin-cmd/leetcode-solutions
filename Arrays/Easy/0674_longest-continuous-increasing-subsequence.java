class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int ans = 1;
        int curr=1;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i] < nums[i+1]){
                curr+=1;
                ans = Math.max(curr,ans);
            }
            else{
                curr=1;
            }
        }
        return ans;
    }
}