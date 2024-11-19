import java.util.*;

class Solution {
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0, -1,1};
    static int n, m;
    public int numIslands(char[][] grid) {
        n = grid.length;
        m = grid[0].length;
        int cnt = 0;
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<m;j++) {
                if (grid[i][j] == '0') continue;
                bfs(i, j, grid);
                cnt++;
            }
        }    
        return cnt;    
    }
    static void bfs(int sy, int sx, char[][] map) {
        Queue<int[]> queue =new ArrayDeque<>();
        queue.add(new int[]{sy, sx});
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            for(int dir = 0;dir<4;dir++) {
                int ny = now[0] + dy[dir];
                int nx = now[1] + dx[dir];
                if (ny >= n || ny < 0 || nx >= m || nx < 0 || map[ny][nx] == '0') continue;
                map[ny][nx] = '0';
                queue.add(new int[]{ny, nx});
            }
        }
    }
}