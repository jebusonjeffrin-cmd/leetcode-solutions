class Solution {
    public int minPathSum(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        int[][] dp = new int[R][C];
        dp[0][0] = grid[0][0];
        for(int i=1;i<C;i++)dp[0][i] = dp[0][i-1] + grid[0][i];
        for(int i=1;i<R;i++)dp[i][0] = dp[i-1][0] + grid[i][0];
        for(int row=1;row<R;row++){
            for(int col=1;col<C;col++){
                dp[row][col] = grid[row][col] + Math.min(dp[row-1][col],dp[row][col-1]);
            }
        }
        return dp[R-1][C-1];
    }
}