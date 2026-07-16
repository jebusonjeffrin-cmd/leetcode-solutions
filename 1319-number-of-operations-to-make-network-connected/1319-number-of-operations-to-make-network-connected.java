class Solution {
    static int[] rank;
    static int[] parent;
    public int makeConnected(int n, int[][] connections) {
        rank = new int[n];
        parent = new int[n];
        for(int i=0;i<n;i++)parent[i] = i;
        int ans=0,extra=0;
        for(int[] connection:connections){
            int u = connection[0];
            int v = connection[1];
            if(findUpar(parent[u]) == findUpar(parent[v]))extra++;
            unionByRank(u,v);
        }
        for(int i=0;i<n;i++){
            if(findUpar(parent[i]) == i)ans++;

        }
        if(extra >= ans-1)return ans-1;
        return -1;
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