class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long[][] horizontal = new long[m][n];
        long[][] vertical = new long[m][n];
        boolean ans = false;
        for(int i=0;i<m;i++){
            horizontal[i][0] = grid[i][0];
            for(int j=1;j<n;j++){
                horizontal[i][j] = grid[i][j] + horizontal[i][j-1];
            }
        }
        for(int j=0;j<n;j++){
            vertical[0][j] = grid[0][j];
            for(int i=1;i<m;i++){
                vertical[i][j] = grid[i][j] + vertical[i-1][j];
            }
        }

        long TotalHori=0;
        for(int i=0;i<m;i++){
            TotalHori+=horizontal[i][n-1];
        }
        long currHori=0;
        for(int i=0;i<m;i++){
            currHori+=horizontal[i][n-1];
            TotalHori-=horizontal[i][n-1];
            if(currHori == TotalHori)return true;
            if(currHori > TotalHori){
                ans = false;
                break;
            }
        }
        long TotalVerti=0;
        for(int j=0;j<n;j++){
            TotalVerti+=vertical[m-1][j];
        }
        long currVerti=0;
        for(int j=0;j<n;j++){
            currVerti+=vertical[m-1][j];
            TotalVerti-=vertical[m-1][j];
            if(currVerti == TotalVerti)return true;
            if(currVerti > TotalVerti){
                ans = false;
                break;
            }
        }
        return ans;
    }
}