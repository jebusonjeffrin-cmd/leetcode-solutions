class Solution {
    List<List<String>> ans = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        Queens(board,0);
        return ans;

    }
    private void Queens(boolean[][] board,int row){
        if(row == board.length){
            Insert(board);
            return ;
        }
        for(int col = 0;col<board.length;col++){
            if(issafe(board,row,col)){
                board[row][col] = true;
                Queens(board,row+1);
                board[row][col] = false;
            }
        }
    
    }
    private boolean issafe(boolean[][]board,int row,int col){
        //vertical check
        for(int i=0;i<row;i++){
            if(board[i][col])
                return false;
        }
        //left diagonal check
        int leftmax = Math.min(row,col);
        for(int i=1;i<=leftmax;i++){
            if(board[row-i][col-i])
                return false;
        }
        int rightmax = Math.min(row,board.length-col-1);
        for(int i=1;i<=rightmax;i++){
            if(board[row-i][col+i])
                return false;
        }
        return true;
    }
    private void Insert(boolean[][] board){
        List<String> list = new ArrayList<>();

        for(int i=0;i<board.length;i++){
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<board.length;j++){
                if(board[i][j])sb.append('Q');
                else sb.append('.');
            }
            list.add(sb.toString());
        }
        ans.add(list);
        return;
    }
}