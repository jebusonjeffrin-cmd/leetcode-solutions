class Solution {
    int count = 0;
    public int numIslands(char[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        for(int row=0;row<R;row++){
            for(int col=0;col<C;col++){
                if(grid[row][col] == '1'){
                    dfs(grid,R,C,row,col);
                    count++;
                }
            }
        }
        return count;
    }
    int[][] neighbours = {{-1,0},{0,-1},{1,0},{0,1}};
    private void dfs(char[][] grid,int R,int C,int row,int col){
        grid[row][col] = '0';
        for(int idx=0;idx<4;idx++){
            int adjR = row+neighbours[idx][0];
            int adjC = col+neighbours[idx][1];
            if(adjR >= 0 && adjR < R && adjC >= 0 && adjC < C && grid[adjR][adjC] == '1'){
                dfs(grid,R,C,adjR,adjC);
            }
        }
    }
}