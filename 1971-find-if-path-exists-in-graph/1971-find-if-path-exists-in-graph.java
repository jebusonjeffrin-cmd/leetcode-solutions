class Solution {
    static int[] rank;
    static int[] parent;
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        rank = new int[n];
        parent = new int[n];
        for(int i=0;i<n;i++)parent[i] = i;
        for(int[] edge:edges){
            int u = edge[0];int v = edge[1];
            unionByRank(u,v);
        }
        if(findUpar(parent[source]) == findUpar(parent[destination]))return true;
        return false;
    }
    private int findUpar(int n){
        if(n == parent[n])return n;
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