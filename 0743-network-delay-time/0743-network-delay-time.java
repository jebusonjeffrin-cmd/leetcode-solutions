class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<ArrayList<int[]>> AdjL = new ArrayList<>();
        for(int i=0;i<=n;i++){
            AdjL.add(new ArrayList<>());
        }
        for(int[] time:times){
            AdjL.get(time[0]).add(new int[]{time[2],time[1]});
        }


        PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->a[0]-b[0]);

        int[] dist = new int[n+1];
        Arrays.fill(dist,(int)1e9);
        dist[k] = 0;
        q.offer(new int[]{0,k});

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int d = curr[0];
            int u = curr[1];

            if(d > dist[u])continue;

            for(int[] adj:AdjL.get(u)){
                if(adj[0] + d < dist[adj[1]]){
                    dist[adj[1]] = adj[0] + d;
                    q.offer(new int[]{dist[adj[1]] ,adj[1]});
                }
            }
        }
        int mx = Integer.MIN_VALUE;
        for(int i=1;i<=n;i++){
            if(dist[i] == (int)1e9)return -1;
            mx = Math.max(mx,dist[i]);
        }
        return mx;
    }
}