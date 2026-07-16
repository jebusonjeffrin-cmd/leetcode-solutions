class Solution {
    static int[] rank;
    static int[] parent;
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        rank = new int[n+1];
        parent = new int[n+1]; 
        for(int i=0;i<=n;i++)parent[i] = i;
        for(int row=0;row<n;row++){
            for(int col=0;col<n;col++){
                if(row != col && isConnected[row][col] == 1){
                    unionByRank(row+1,col+1);
                }
            }
        }
        int ans=0;
        for(int i=1;i<=n;i++){
            if(parent[i] == i)ans++;
        }
        return ans;
    }
    private int findUpar(int n){
        if(n == parent[n]){
            return n;
        }
        return parent[n] = findUpar(parent[n]);
    }
    private void unionByRank(int u,int v){
        int ulp_u = findUpar(u);
        int ulp_v = findUpar(v);
        if(ulp_u == ulp_v)return;
        if(rank[ulp_u] < rank[ulp_v]){
            parent[ulp_u] = ulp_v;
        }
        else if(rank[ulp_v] < rank[ulp_u]){
            parent[ulp_v] = ulp_u;
        }
        else{
            parent[ulp_v] = ulp_u;
            rank[ulp_u]++;
        }
    }
}