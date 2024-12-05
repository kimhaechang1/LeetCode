class Solution {
    static int n;
    static int m;
    static boolean find;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    public boolean exist(char[][] board, String word) {
        find = false;
        // 동일한 칸의 문자를 2번이상 사용할 수 없다.
        // 서로다른 칸이라면 문자가 같더라도 중복해서 사용할 수 있다.
        n = board.length;
        m = board[0].length;
        boolean[][] v = new boolean[n][m];
        for(int i = 0;i < n; i++) {
            for(int j = 0;j < m; j++) {
                if (board[i][j] == word.charAt(0)) {
                    v[i][j] = true;
                    dfs(i, j, 1, word, board, v);
                    if (find) return find;
                    v[i][j] = false;
                }
            }
        }
        return find;
    }
    static void dfs(int y, int x, int depth, String word, char[][] board, boolean[][] v) {
        if (find) return;
        if (depth == word.length()) {
            find = true;
            return;
        }

        for(int dir = 0; dir < 4; dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if (OOB(ny, nx) || v[ny][nx] || word.charAt(depth) != board[ny][nx]) continue;
            v[ny][nx] = true;
            dfs(ny, nx, depth + 1, word, board, v);
            v[ny][nx] = false;
        }
    }
    static boolean OOB(int y, int x) {
        return y >= n || y < 0 || x >= m || x < 0;
    }
}