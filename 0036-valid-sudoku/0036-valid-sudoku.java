class Solution {
    static boolean isEnd;
    static int n, m;
    public boolean isValidSudoku(char[][] board) {
        // 각 행은 반드시 1 ~ 9 사이의 숫자를 반복없이 가져야한다.
        // 각 열은 반드시 1 ~ 9 사이의 숫자를 반복없이 가져야한다.
        // 각 3 * 3의 그리드 내에 서브박스는 1 ~ 9 숫자를 반복해선 안된다.

        // 각 빈칸에 1 ~ 9사이의 숫자를 넣은 경우로 백트래킹 해야한다.
        // 빈칸에 들어갈 숫자가 어느것도 안된다면, 
        for(int i = 0;i < 9;i++) {
            for(int j = 0;j < 9;j++) {
                if (board[i][j] == '.') continue;
                if (!check(board[i][j] - '0', board, i, j)) return false;
            }
        }
        return true;
    }
    static boolean check(int value, char[][] board, int y, int x) {
        for(int i = 0; i < 9 ;i++) {
            if (i == y || i == x) continue;
            if ((board[y][i] - '0') == value || (board[i][x] - '0') == value) return false;
        }

        int sy = y / 3 * 3;
        int sx = x / 3 * 3;
        for(int i = sy; i < sy + 3; i ++) {
            for(int j = sx; j < sx + 3; j ++) {
                if (i == y && j == x) continue;
                if ((board[i][j] - '0') == value) return false;
            }
        }
        return true;
    }
}