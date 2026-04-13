class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        if(row==1 && col==1 && grid[0][0] == 0)return 1;
        Queue<int[]> q = new LinkedList<>();
        int[][] neighbours = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        if(grid[0][0] == 1)return -1;
        int count=1;
        q.offer(new int[]{0,0});
        grid[0][0] = 1;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                int[] temp = q.poll();
                int r = temp[0];
                int c = temp[1];
                for(int[] neighbour:neighbours){
                    int nr = r+neighbour[0];
                    int nc = c+neighbour[1];
                    if(nr == row-1 && nc == col-1 && grid[nr][nc] == 0){
                        ++count;
                        return count;
                    }
                    if(nr>=0 && nc>=0 && nr<row && nc<col && grid[nr][nc] == 0){
                        q.offer(new int[]{nr,nc});
                        grid[nr][nc] = 1;
                    }
                }
            }
            count++;
        }
        return -1;
    }
}