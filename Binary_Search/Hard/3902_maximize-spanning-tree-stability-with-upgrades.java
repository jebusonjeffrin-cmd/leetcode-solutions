class Solution {

    class DSU {
        int[] parent;
        int[] size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean unite(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) return false;

            if (size[ra] < size[rb]) {
                int t = ra;
                ra = rb;
                rb = t;
            }

            parent[rb] = ra;
            size[ra] += size[rb];
            return true;
        }
    }

    boolean check(long X, int n, int[][] edges, int k) {
        DSU dsu = new DSU(n);
        int comps = n;

        for (int[] e : edges) {
            if (e[3] == 1) {
                if (e[2] < X) return false;
                if (dsu.unite(e[0], e[1])) comps--;
            }
        }

        for (int[] e : edges) {
            if (e[3] == 0 && e[2] >= X) {
                if (dsu.unite(e[0], e[1])) comps--;
            }
        }

        if (comps - 1 > k) return false;

        for (int[] e : edges) {
            if (e[3] == 0 && e[2] < X && 2L * e[2] >= X) {
                if (dsu.unite(e[0], e[1])) comps--;
            }
        }

        return comps == 1;
    }

    public int maxStability(int n, int[][] edges, int k) {

        DSU init = new DSU(n);
        long maxMust = (long)2e9;

        for (int[] e : edges) {
            if (e[3] == 1) {
                if (!init.unite(e[0], e[1])) return -1;
                maxMust = Math.min(maxMust, (long)e[2]);
            }
        }

        DSU full = new DSU(n);
        int comps = n;

        for (int[] e : edges) {
            if (full.unite(e[0], e[1])) comps--;
        }

        if (comps > 1) return -1;

        long low = 1;
        long high = Math.min((long)2e5, maxMust);
        long ans = -1;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (check(mid, n, edges, k)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return (int)ans;
    }
}