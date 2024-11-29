class Solution {
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int n, m;
    static boolean[][] v;
    public List<Integer> spiralOrder(int[][] matrix) {
        n = matrix.length; m = matrix[0].length;
        v = new boolean[n][m];
        int y = 0;
        int x = 0;
        int dir = 3;
        List<Integer> list = new ArrayList<>();
        int cnt = n * m;
        while(cnt-- > 0) {
            list.add(matrix[y][x]);
            v[y][x] = true;
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if (isCorner(ny, nx)) {
                dir = getNextDir(dir);
                ny = y + dy[dir];
                nx = x + dx[dir];
            }
            y = ny;
            x = nx;
        }
        
        return list;
    }

    static boolean isCorner(int ny, int nx) {
        return ny >= n || ny < 0 || nx >= m || nx < 0 || v[ny][nx];
    }
    static int getNextDir(int dir) {
        if (dir == 3) return 1;
        if (dir == 1) return 2;
        if (dir == 2) return 0;
        return 3;
    }
}