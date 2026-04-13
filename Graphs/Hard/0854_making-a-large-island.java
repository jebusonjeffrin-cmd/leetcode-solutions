class Solution {
    int diff[] = {0,1,0,-1,0};
    public int largestIsland(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        int land = 0;
        int ans = 0;
        int[][] visited = new int[R][C];
        for(int r=0;r<R;r++){
            for(int c=0;c<C;c++){
                if(grid[r][c] == 1 && visited[r][c] == 0){
                    dfs(grid,r,c,visited,++land);
                }
            }
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int r=0;r<R;r++){
            for(int c=0;c<C;c++){
                if(visited[r][c] != 0){
                    map.put(visited[r][c],
                    map.getOrDefault(visited[r][c],0)+1);
                }
            }
        }
        for(int val: map.values()){
            ans = Math.max(ans,val);
        }
        for(int r=0;r<R;r++){
            for(int c=0;c<C;c++){

                if(grid[r][c] == 0){

                    HashSet<Integer> set = new HashSet<>();
                    int size = 1;
                    for(int i=0;i<4;i++){
                        int nr = r + diff[i];
                        int nc = c + diff[i+1];
                        if(nr>=0 && nc>=0 && nr<R && nc<C
                           && visited[nr][nc] != 0){

                            set.add(visited[nr][nc]);
                        }
                    }
                    for(int id : set){
                        size += map.get(id);
                    }

                    ans = Math.max(ans,size);
                }
            }
        }

        return ans;
    }

    private void dfs(int[][] grid,int r,int c,int[][] visited,int land){

        if(r<0 || c<0 || r>=grid.length || c>=grid[0].length
           || grid[r][c] == 0 || visited[r][c] != 0){
            return;
        }

        visited[r][c] = land;

        dfs(grid,r,c-1,visited,land);
        dfs(grid,r,c+1,visited,land);
        dfs(grid,r-1,c,visited,land);
        dfs(grid,r+1,c,visited,land);
    }
}