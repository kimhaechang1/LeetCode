class Solution {
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static Queue<int[]> queue;
    static int n, m;
    static int foCnt;
    public int orangesRotting(int[][] grid) {
        // 아니면 프레시 오렌지수가 0이 되면 성공적으로 다썩음
        queue = new ArrayDeque<>();
        foCnt = 0;
        n = grid.length;
        m = grid[0].length;
        for(int i = 0;i<grid.length;i++) {
            for(int j = 0;j<grid[i].length;j++) {
                if (grid[i][j] == 2) queue.add(new int[]{i, j});
                if (grid[i][j] == 1) foCnt++;
            }
        }
        return bfs(grid);
    }
    static int bfs(int[][] map) {
        int time = 0;
        label: while(!queue.isEmpty()) {
            int sz = queue.size();
            if (foCnt == 0) break;
            time++;
            for(int i = 0;i<sz;i++) {
                int[] now = queue.poll();
                for(int dir = 0;dir<4;dir++) {
                    int ny = now[0] + dy[dir];
                    int nx = now[1] + dx[dir];
                    if (ny >= n || ny < 0 || nx >= m || nx < 0 || map[ny][nx] != 1) continue;
                    foCnt--;
                    if (foCnt == 0) break label;
                    map[ny][nx] = 2;
                    queue.add(new int[]{ny, nx});
                }
            }
        }
        return foCnt == 0 ? time : -1;
    }
}