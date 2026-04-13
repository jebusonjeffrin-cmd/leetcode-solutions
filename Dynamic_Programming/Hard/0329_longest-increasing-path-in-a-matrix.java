class Solution {
    int[] adjR = {0, 0, -1, 1};
    int[] adjC = {-1, 1, 0, 0};

    public int longestIncreasingPath(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;

        int[][] path = new int[R][C];  
        int max = 0;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                max = Math.max(max, dfs(r, c, matrix, path));
            }
        }

        return max;
    }

    private int dfs(int r, int c, int[][] matrix, int[][] path) {

        if (path[r][c] != 0) return path[r][c];

        int R = matrix.length;
        int C = matrix[0].length;

        int maxLen = 1; 
        for (int i = 0; i < 4; i++) {
            int newR = r + adjR[i];
            int newC = c + adjC[i];

            if (newR >= 0 && newC >= 0 && newR < R && newC < C &&
                matrix[newR][newC] > matrix[r][c]) {

                maxLen = Math.max(maxLen, 1 + dfs(newR, newC, matrix, path));
            }
        }

        path[r][c] = maxLen; 
        return maxLen;
    }
}