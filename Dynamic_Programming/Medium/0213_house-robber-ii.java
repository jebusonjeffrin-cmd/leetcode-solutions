class Solution {
    public int rob(int[] nums) {
       int n = nums.length;
       if(n==1)return nums[0];
       int[] dp = new int[n];
       Arrays.fill(dp,-1);
        int case1 = check(nums,0,n-1,dp);

        int[] dp2 = new int[n];
        Arrays.fill(dp2,-1);
        int case2 = check(nums,1,n,dp2);
        return Math.max(case1,case2);
       
    }
    private int check(int[] nums,int n,int end,int[] dp){
        if(n>=end){
            return 0;
        }
        if(dp[n] != -1)return dp[n]; 
        int pick = nums[n] + check(nums,n+2,end,dp);
        int nopick = check(nums,n+1,end,dp);
        return dp[n] = Math.max(pick,nopick);
    }
}