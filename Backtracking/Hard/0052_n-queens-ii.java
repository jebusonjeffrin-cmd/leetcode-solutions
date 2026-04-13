class Solution {
    public int totalNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        return queen(board,0);
    }
    private int queen(boolean[][] board,int row){
        if(row == board.length){
            return 1;
        }
        int count=0;
        for(int col=0;col<board.length;col++){
            if(issafe(board,row,col)){
                board[row][col] = true;
                count+=queen(board,row+1);
                board[row][col] = false;
            }
            
        }
        return count;
    }
    private boolean issafe(boolean[][] board,int row ,int col){
        //vertical check
        for(int i=0;i<row;i++){
            if(board[i][col]){
                return false;
            }
        }
        //left diagonal check
        int leftmax = Math.min(row,col);
        for(int i=1;i<=leftmax;i++){
            if(board[row-i][col-i]){
                return false;
            }
        }
        //right diagonal check
        int rightmax = Math.min(row,board.length-col-1);
        for(int i=1;i<=rightmax;i++){
            if(board[row-i][col+i]){
                return false;
            }
        }
        return true;
    }
}