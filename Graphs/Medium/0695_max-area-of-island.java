class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        int count = 0 , maxcount = 0;
        for(int row=0;row<R;row++){
            for(int col=0;col<C;col++){
                if(grid[row][col] == 1){
                count = dfs(grid,R,C,row,col);
                maxcount = Math.max(maxcount,count);
                }
            }
        }
        return maxcount;
    }
    int[][] diff = {{-1,0},{0,-1},{1,0},{0,1}};
    private int dfs(int[][] grid,int R,int C,int row,int col){
        grid[row][col] = 0;
        int count = 1;
        for(int idx=0;idx<4;idx++){
            int adjR = row+diff[idx][0];
            int adjC = col+diff[idx][1];
            if(adjR >= 0 && adjR < R && adjC >= 0 && adjC < C && grid[adjR][adjC] == 1){
                count += dfs(grid,R,C,adjR,adjC);
            }
        }
        return count;
    }
}