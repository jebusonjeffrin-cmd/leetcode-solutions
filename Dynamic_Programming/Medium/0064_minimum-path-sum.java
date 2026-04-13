class Solution {
    public int minPathSum(int[][] grid) {
        int[][]dp = new int[grid.length][grid[0].length];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        return minpathsum(grid,0,0,dp);
    }
    private int minpathsum(int[][] grid,int r ,int c,int[][] dp){
        if(r>=grid.length || c>=grid[0].length){
            return Integer.MAX_VALUE;
        }
        if(r == grid.length-1 && c == grid[0].length-1){
            return grid[r][c];
        }
        if(dp[r][c] != -1)return dp[r][c];
        int right = minpathsum(grid,r,c+1,dp);
        int down = minpathsum(grid,r+1,c,dp);
        return dp[r][c] =  grid[r][c]+Math.min(right,down);
    }
}