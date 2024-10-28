class Solution {
    static int[][] ans;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int n;
    static int m;
    static boolean[][] v;
    static Queue<int[]> queue;
    public int[][] updateMatrix(int[][] mat) {
        // implementation
        n = mat.length;
        m = mat[0].length;
        ans = new int[n][m];
        v = new boolean[n][m];
        queue = new ArrayDeque<>();
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<m;j++) {
                if (mat[i][j] == 0) {
                    v[i][j] = true;
                    queue.add(new int[]{i, j, 0});
                } else {
                    ans[i][j] = 987654321;
                }
            }
        }
        bfs(mat);
        return ans;
    }
    static void bfs(int[][] map) {
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            for(int dir = 0;dir<4;dir++) {
                int ny = now[0] + dy[dir];
                int nx = now[1] + dx[dir];
                if (OOB(ny, nx) || v[ny][nx] || map[ny][nx] == 0) continue;
                if (ans[ny][nx] > now[2] + 1) {
                    v[ny][nx] = true;
                    ans[ny][nx] = now[2] + 1;
                    queue.add(new int[]{ny, nx, now[2] + 1});
                }
                 
            }
        }
    }
    
    static boolean OOB(int y, int x) {
        return y >= n || y < 0 || x >= m || x < 0;
    }
    
}