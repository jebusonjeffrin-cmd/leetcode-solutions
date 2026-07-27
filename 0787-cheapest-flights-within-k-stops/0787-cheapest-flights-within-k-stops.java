class Solution {
    public int findCheapestPrice(int N, int[][] flights, int src, int dst, int K) {
        List<List<int[]>> AdjL = new ArrayList<>();
        for(int i=0;i<N;i++){   
            AdjL.add(new ArrayList<>());
        }
        for(int[] flight:flights){
            AdjL.get(flight[0]).add(new int[]{flight[1],flight[2]});
        }
        int[] distance = new int[N];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[src] = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,src,0});
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int dist = curr[2];
            int node = curr[1];
            int steps = curr[0];
            if(steps > K)continue;
            for(int[] Adj:AdjL.get(node)){
                int Adj_node = Adj[0];
                int Adj_dist = Adj[1];
                if(dist + Adj_dist < distance[Adj_node]){
                    distance[Adj_node] = dist + Adj_dist;
                    q.offer(new int[]{steps+1,Adj_node,distance[Adj_node]});
                }
            }
        }
        return (distance[dst] == Integer.MAX_VALUE)?-1:distance[dst];
    }
}