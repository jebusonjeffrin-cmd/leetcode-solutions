class Solution {
    public void solveSudoku(char[][] board) {
        PlaceNumber(board,0,0);
    }
    private boolean PlaceNumber(char[][] board,int r,int c){
        if(r==9){
            return true;
        }
        if(c==9){
            return PlaceNumber(board,r+1,0);
            
        }
        if(board[r][c] != '.'){
            return PlaceNumber(board,r,c+1);

        }
        for(int i=1;i<10;i++){
            if(issafe(board,r,c,i)){
                board[r][c] =(char)(i+'0');
                if(PlaceNumber(board,r,c+1))return true;
                board[r][c] = '.';
            }
        }
        return false;
    }
    private boolean issafe(char[][] board,int r,int c,int num){
        for(int i=0;i<board.length;i++){
            if(board[r][i] == num+'0'){ // rows
                return false;
            }
        }
        for(int i=0;i<board.length;i++){
            if(board[i][c] == num+'0'){ // cols
                return false;
            }
        }
        int strrow = r-r%3;
        int strcol = c-c%3;
        for(int i=strrow;i<strrow+3;i++){
            for(int j=strcol;j<strcol+3;j++){
                if(board[i][j] == num+'0'){
                    return false;
                }
            }
        }
        return true;

    }
}