class Solution {
    public int minInsertions(String s) {
       int n = s.length();
        int[][] dp= new int[n][n];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        char[] str = s.toCharArray();
        int l=0,r=n-1;
        while(l<r){
            char temp = str[l];
            str[l] = str[r];
            str[r] = temp;
            l++;r--;
        }
        String rev = new String(str);
        int rem = solve(n-1,n-1,s,rev,dp);
        return n-rem;
    }
    private int solve(int i,int j,String s1,String s2,int[][] dp){
        if(i<0 || j<0)return 0;
        if(dp[i][j] != -1)return dp[i][j];
        if(s1.charAt(i) == s2.charAt(j)){
            return dp[i][j] = 1+solve(i-1,j-1,s1,s2,dp);
        }
        return dp[i][j] = Math.max(solve(i-1,j,s1,s2,dp),solve(i,j-1,s1,s2,dp));
    }
}