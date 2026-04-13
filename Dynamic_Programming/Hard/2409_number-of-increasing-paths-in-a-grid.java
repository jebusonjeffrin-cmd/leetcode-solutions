
class Solution {
    int[] adjR = {0, 0, -1, 1};
    int[] adjC = {-1, 1, 0, 0};
    int MOD = 1_000_000_007;

    public int countPaths(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;

        long total = 0;

        int[][] path = new int[R][C];

        for (int i = 0; i < R; i++) {
            Arrays.fill(path[i], -1);
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                total = (total + dfs(r, c, grid, path)) % MOD;
            }
        }

        return (int) total;
    }

    private int dfs(int r, int c, int[][] grid, int[][] path) {

        if (path[r][c] != -1) {
            return path[r][c];
        }

        int R = grid.length;
        int C = grid[0].length;

        long count = 1;

        for (int i = 0; i < 4; i++) {
            int nr = r + adjR[i];
            int nc = c + adjC[i];

            if (nr >= 0 && nc >= 0 && nr < R && nc < C) {

                if (grid[nr][nc] > grid[r][c]) {
                    count = (count + dfs(nr, nc, grid, path)) % MOD;
                }
            }
        }

        path[r][c] = (int) count;
        return path[r][c];
    }
}