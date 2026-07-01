class Solution {
    static int R,C;
    static int[][] diff = {{-1,0},{1,0},{0,-1},{0,1}};
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        R = grid.size();
        C = grid.get(0).size();
        if(grid.get(0).get(0) == 1 || grid.get(R-1).get(C-1) == 1)return 0;
        Queue<int[]> q = new LinkedList<>();
        List<List<Boolean>> visited = new ArrayList<>();
        for(int row=0;row<R;row++){
            List<Boolean> lst = new ArrayList<>();
            for(int i=0;i<C;i++)lst.add(false);
            visited.add(lst);
        }
        for(int row=0;row<R;row++){
            for(int col=0;col<C;col++){
                if(grid.get(row).get(col) == 1){
                    q.offer(new int[]{row,col});
                    grid.get(row).set(col,0);
                    visited.get(row).set(col,true);
                }
            }
        }
        int level=1;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                int[] temp = q.poll();
                int row = temp[0];
                int col = temp[1];
                for(int j=0;j<4;j++){
                    int adjR = row+diff[j][0];
                    int adjC = col+diff[j][1];
                    if(adjR >=0 && adjR < R && adjC >=0 && adjC < C && !visited.get(adjR).get(adjC)){
                        q.offer(new int[]{adjR,adjC});
                        grid.get(adjR).set(adjC,level);
                        visited.get(adjR).set(adjC,true);
                    }
                }
            }
            level++;
        }
        
        int start=0,end=0,res=-1;
        for(int row=0;row<R;row++){
            for(int col=0;col<C;col++){
                if(grid.get(row).get(col) > end){
                    end = Math.max(grid.get(row).get(col),end);
                }
            }
        }
        while(start <= end){
            int mid = (start+end)/2;
            if(isValidSafeness(grid,mid)){
                res = mid;
                start = mid+1;
            }else end = mid-1;
        }
        return res;
    }
    private boolean isValidSafeness(List<List<Integer>> grid,int val){
        if(val > grid.get(0).get(0) || val>grid.get(R-1).get(C-1
        ))return false;
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> q = new LinkedList<>();
        visited[0][0] = true;
        q.offer(new int[]{0,0});
        while(!q.isEmpty()){
            int[] curr = q.poll();
            if(curr[0] == R-1 && curr[1] == C-1)return true;
            for(int i=0;i<4;i++){
                int adjR = curr[0]+diff[i][0];
                int adjC = curr[1]+diff[i][1];
                if(adjR >=0 && adjR <R && adjC >=0 && adjC < C && !visited[adjR][adjC] &&
                grid.get(adjR).get(adjC) >= val){
                    visited[adjR][adjC] = true;
                    q.offer(new int[]{adjR,adjC});
                }
            }
        }
        return false;
    }
}