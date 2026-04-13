class Solution {
    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        return bfs(arr,start,visited);
    }
    private boolean bfs(int[] arr, int pos,boolean[] visited){
        if(pos >= arr.length || pos<0 || visited[pos])return false;
        if(arr[pos] == 0)return true;
        visited[pos] = true;
        return bfs(arr,pos+arr[pos],visited) || bfs(arr,pos-arr[pos],visited);
    }
}