class Solution {
    int[][] neighbours = {{-1,0},{0,-1},{1,0},{0,1}};
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int R = image.length;
        int C = image[0].length;
        int originalcolor = image[sr][sc];
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        q.offer(new int[]{sr,sc});
        image[sr][sc] = color;
        visited[sr][sc] = true;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                int[] temp = q.poll();
                int row = temp[0];
                int col = temp[1];
                for(int[] neighbour : neighbours){
                    int adjR = row + neighbour[0];
                    int adjC = col + neighbour[1];
                    if(adjR >= 0 && adjC >=0 && adjR < R && adjC < C && image[adjR][adjC] == originalcolor && !visited[adjR][adjC]){
                        q.offer(new int[]{adjR,adjC});
                        image[adjR][adjC] = color;
                        visited[adjR][adjC] = true;
                    }
                }
            }
        }
        return image;
    }
}