class Solution {
    public int numSpecial(int[][] mat) {
        int R = mat.length;
        int C = mat[0].length;
        int count=0;
        for(int r=0;r<R;r++){
            for(int c=0;c<C;c++){
                if(mat[r][c] == 1){
                    if(check(mat,r,c)){
                        count++;
                }
                }
            }
        }
        return count;
    }
    private boolean check(int[][] mat,int r,int c){
        int R=mat.length;
        int C=mat[0].length;
        for(int i=0;i<C;i++){
            if(i==c)continue;
            if(mat[r][i] == 1)return false;
        }
        for(int i=0;i<R;i++){
            if(i==r)continue;
            if(mat[i][c] == 1)return false;
        }
        return true;
    }
}