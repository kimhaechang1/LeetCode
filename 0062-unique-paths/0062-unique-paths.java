class Solution {
    static int N;
    static int M;
    public int uniquePaths(int m, int n) {
        N = m;
        M = n;
        // 로봇은 초기에 좌상 코너에 위치되어 있다.
        // 로벗은 우하 코너로 이동하려한다.
        // 로봇은 오직 하 혹은 우 로만 이동이 가능하다.
        // 우하 코너로 이동할 수 있는 유니크한 경로의 개수

        int[][] dp =  new int[N][M];
        
        for(int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }
        for(int i = 0; i < M; i++) {
            dp[0][i] = 1;
        }

        for(int i = 1;i < N; i++) {
            for(int j = 1;j < M; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[N - 1][M - 1];
    }
}