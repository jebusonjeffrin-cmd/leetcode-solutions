class Solution {
    static int[][] diff = {{-1,0},{1,0},{0,1},{0,-1}};
    static int R,C;
    public boolean findSafeWalk(List<List<Integer>> g, int health) {
        R = g.size();C = g.get(0).size();
        int[][] grid = new int[R][C];
        for(int row=0;row<R;row++){
            for(int col=0;col<C;col++){
                grid[row][col] = g.get(row).get(col);
            }
        }
        boolean[][] visited = new boolean[R][C];
        Boolean[][][] dp = new Boolean[R][C][health+1];
        return dfs(grid,visited,health,0,0,R,C,dp);
    }
    private boolean dfs(int[][] grid,boolean[][] visited,int health,int row,int col,int R,int C,Boolean[][][] dp){
        if(grid[row][col] == 1)health--;
        if(health <=0){
            return false;
        }
        if(row == R-1 && col == C-1){
            return true;
        }
        if(dp[row][col][health] != null)return dp[row][col][health];
        visited[row][col] = true;
        for(int i=0;i<4;i++){
            int adjR = row+diff[i][0];
            int adjC = col+diff[i][1];
            if(isValid(grid,adjR,adjC,R,C) && !visited[adjR][adjC]){
                if(dfs(grid,visited,health,adjR,adjC,R,C,dp)){
                    return dp[adjR][adjC][health] = true;
                }
                
            }
        }
        visited[row][col] = false;
        return dp[row][col][health] = false;
    }
    private boolean isValid(int[][] grid,int row,int col,int R,int C){
        if(row >=0 && row <R && col>=0 && col < C)return true;
        return false;
    }
}