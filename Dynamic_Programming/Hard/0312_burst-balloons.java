class Solution {
    public int maxCoins(int[] nums) {
        int N = nums.length;
        Integer[][] dp = new Integer[N][N];
        return burst(nums,dp,0,N-1,N);
    }
    private int burst(int[] nums,Integer[][] dp,int l,int r,int N){
        if(l>r)return 0;
        if(dp[l][r] != null)return dp[l][r];
        int maxcoins = 0;
        for(int i=l;i<=r;i++){
            int currcoins = burst(nums,dp,l,i-1,N) + burst(nums,dp,i+1,r,N);
            int thislast = nums[i] * (l-1>=0?nums[l-1]:1) * (r+1<N?nums[r+1]:1);
            currcoins+=thislast;
            maxcoins = Math.max(maxcoins,currcoins);
        }
        dp[l][r] = maxcoins;
        return maxcoins;
    }
}