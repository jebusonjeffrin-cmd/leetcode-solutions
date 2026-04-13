class Solution {
    public boolean isValidSudoku(char[][] board) {
        int R=9,C=9;
        for(int r=0;r<R;r++){
            for(int c=0;c<C;c++){
                if(board[r][c] == '.')continue;
                int num = board[r][c] - '0';
                if(!issafe(board,r,c,num)){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean issafe(char[][] board,int r,int c,int num){
        int count=0;
        for(int i=0;i<9;i++){
            if(board[r][i] == num+'0'){
                count++;
            }
        }
        for(int i=0;i<9;i++){
            if(board[i][c]==num+'0'){
                count++;
            }
        }
        int row = r-r%3;
        int col = c-c%3;
        for(int i=row;i<row+3;i++){
            for(int j=col;j<col+3;j++){
                if(board[i][j] == num+'0'){
                    count++;
                }
            }
        }
        if(count>3){
            return false;
        }
        return true;
    }
}