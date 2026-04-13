class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++){
            Arrays.fill(dp[i],-1);
        }
        //if(obstacleGrid.length <=1 && obstacleGrid[0][0] != 0){
           // return 0;
        //}
        return solve( obstacleGrid, m-1 , n-1, dp);

        
    }
    private int solve(int[][] grid,int m ,int n,int[][] dp){
       
        if(m<0 || n<0){
            return 0;
        }
        if(grid[m][n] == 1){
            return 0;
        }
        if(m==0 && n ==0){
            return 1;
        }
        if(dp[m][n] != -1){
            return dp[m][n];
        }
        return dp[m][n] = solve(grid,m,n-1,dp) + solve(grid,m-1,n,dp); 
    }
}