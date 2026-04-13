class Solution {
    public int tribonacci(int n) {
        int[] dp = new int[n+1];
        for(int i=0;i<=n;i++){
            dp[i] = -1;
        }
        return tribonacci(n,dp);
    }
    private int tribonacci(int n,int[] dp){
        if(n<2){
            return n;
        }
        if(n==2){
            return 1;
        }
        if(dp[n] !=-1){
            return dp[n];
        }
        dp[n] = tribonacci(n-1,dp)+tribonacci(n-2,dp)+tribonacci(n-3,dp);
        return dp[n];
    }
}