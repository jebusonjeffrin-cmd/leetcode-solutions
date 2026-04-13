class Solution {
    public int uniquePathsIII(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        int zerocount = 0;
        int count = 0;
        for(int row=0;row<R;row++){
            for(int col=0;col<C;col++){
                if(grid[row][col] == 0){
                    zerocount++;
                }
            }
        }
        boolean[][] visited = new boolean[R][C];
        for(int row=0;row<R;row++){
            for(int col=0;col<C;col++){
                if(grid[row][col] == 1){
                    count = dfs(grid,visited,R,C,row,col,zerocount);
                    return count;
                }
            }
        }
        return count;
    }
    int[][] diff = {{-1,0},{0,-1},{0,1},{1,0}};
    private int dfs(int[][] grid,boolean[][] visited,int R,int C,int row,int col,int zerocount){
        visited[row][col] = true;
        if(grid[row][col] == 2){
            visited[row][col] = false;
            return zerocount==0 ? 1:0;
        }
        
        int count = 0;
        for(int i=0;i<4;i++){
            int adjR = row+diff[i][0];
            int adjC = col+diff[i][1];
            
            if(adjR >=0 && adjC >=0 && adjR < R && adjC < C && grid[adjR][adjC] != -1 && !visited[adjR][adjC]){
                if(grid[adjR][adjC] == 0){
                    count += dfs(grid,visited,R,C,adjR,adjC,zerocount-1);
                }
                else{
                    count += dfs(grid,visited,R,C,adjR,adjC,zerocount);
                }
            }
        }
        visited[row][col] = false;
        return count;
    }
}