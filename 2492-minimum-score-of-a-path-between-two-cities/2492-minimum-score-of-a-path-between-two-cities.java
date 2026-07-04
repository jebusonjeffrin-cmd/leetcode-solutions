class Solution {
    class pair{
        int first,second;
        pair(int first,int second){
            this.first = first;
            this.second = second;
        }
    }
    static int ans = Integer.MAX_VALUE;
    public int minScore(int n, int[][] roads) {
        ArrayList<ArrayList<pair>> AdjL = new ArrayList<>();
        int len = roads.length;
        for(int i=0;i<=n;i++){
            AdjL.add(new ArrayList<>());
        }
        for(int i=0;i<len;i++){
            int u = roads[i][0]; 
            int v = roads[i][1];
            int wt = roads[i][2];
            AdjL.get(u).add(new pair(v,wt));
            AdjL.get(v).add(new pair(u,wt)); 
        }
        // for(int i=1;i<=n;i++){
        //     for(pair it:AdjL.get(i)){
        //         System.out.println(it.first+" "+it.second);
        //     }
        // }
        boolean[] visited = new boolean[n+1];
        ans = Integer.MAX_VALUE;
        dfs(AdjL,visited,1);
        return ans;
    }
    private void dfs(ArrayList<ArrayList<pair>> AdjL,boolean[] visited,int node){
        visited[node] = true;
        for(pair it:AdjL.get(node)){
            int v = it.first;
            ans = Math.min(ans,it.second);
            if(!visited[v]){
            dfs(AdjL,visited,v);
            }
        }
    }
}