class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len2+1][len1+1];
        int max=0;
        for(int row=1;row<=len2;row++){
            for(int col=1;col<=len1;col++){
                if(text2.charAt(row-1) != text1.charAt(col-1)){ 
                    dp[row][col] = Math.max(dp[row][col-1],dp[row-1][col]);
                }
                else{
                    dp[row][col] = dp[row-1][col-1]+1;
                }
                max = Math.max(max,dp[row][col]);
            }
        }
        return max;
    }
}