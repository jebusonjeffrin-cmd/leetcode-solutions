class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int R = isConnected.length;
        int C = isConnected[0].length;
        int provinces=0;
        for(int r=0;r<R;r++){
            for(int c=0;c<C;c++){
                if(!visited[r]){
                    dfs(isConnected,visited,r);
                    provinces++;
                }
            }
        }
        return provinces;
    }
    private void dfs(int[][] isConnected,boolean[] visited,int row){
        visited[row] = true;
        for(int i=0;i<visited.length;i++){
            if(isConnected[row][i] == 1 && !visited[i]){
                dfs(isConnected,visited,i);
            }
        }
    }
}