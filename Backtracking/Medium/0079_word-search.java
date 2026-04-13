class Solution {
    int[] adjR={0,0,-1,1};
    int[] adjC={-1,1,0,0};
    public boolean exist(char[][] board, String word) {
        int R=board.length;
        int C=board[0].length;
        for(int r=0;r<R;r++){
            for(int c=0;c<C;c++){
                if(board[r][c] == word.charAt(0)){
                if(dfs(board,r,c,word,0)){
                    return true;
                }
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board,int r,int c,String word,int idx){
        //base case
        if(idx == word.length()){
            return true;
        }
        //border conditions and board conditions
        if(r>=board.length || c>=board[0].length || r<0 || c<0 || board[r][c] != word.charAt(idx)){
            return false;
        }
        char ch = board[r][c];   //backup the value
        board[r][c] = '*';
        for(int i=0;i<4;i++){
            int newR = r+adjR[i];
            int newC = c+adjC[i];
            if(dfs(board,newR,newC,word,idx+1)){
                return true;}
        }
        board[r][c] = ch;//backtrack
        return false;
    }
}