class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum=0;
        for(int i=0;i<n;i++){
            sum+=nums[i];
        }
        if(sum%2!=0)return false;
        Boolean[][] dp = new Boolean[n][sum];
        if(check(nums,sum/2,0,dp)){
            return true;
        }
        return false;
    }
    private boolean check(int[] nums,int target,int idx,Boolean[][] dp){
        if(target == 0){
            return true;
        }
        if(idx == nums.length || target<0){
            return false;
        }
        if(dp[idx][target] != null){
            return dp[idx][target];
        }
        boolean take = check(nums,target-nums[idx],idx+1,dp);
        boolean nottake = check(nums,target,idx+1,dp);
        return dp[idx][target] = take||nottake;
    }
}