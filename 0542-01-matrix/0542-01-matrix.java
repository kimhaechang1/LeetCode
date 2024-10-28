class Solution {
    static int[][] ans;
    static int[] dy = {-1,1,0,0,1,1,-1,-1};
    static int[] dx = {0,0,-1,1,-1,1,-1,1};
    static int n;
    static int m;
    public int[][] updateMatrix(int[][] mat) {
        // implementation
        n = mat.length;
        m = mat[0].length;
        ans = new int[n][m];
        // 왼쪽에서 오른쪽
        // 오른쪽에서 왼쪽, 위에서 아래, 아래에서 위
        int[][] map1 = new int[n][m];
        int[][] map2 = new int[n][m];
        int[][] map3 = new int[n][m];
        int[][] map4 = new int[n][m];


        for(int i = 0;i<n;i++) {
            int sum = 0;
            for(int j = 0;j<m;j++) {
                if (mat[i][j] != 0 && sum + mat[i][j] > 0) {
                    sum += mat[i][j];
                    map1[i][j] = sum;
                } else if (mat[i][j] == 0) {
                    sum = 0;
                }
            }
            sum = 0;
            for(int j = m-1;j>-1;j--) {
                if (mat[i][j] != 0 && sum + mat[i][j] > 0) {
                    sum += mat[i][j];
                    map2[i][j] = sum;
                } else if (mat[i][j] == 0) {
                    sum = 0;
                }
            }
        }
        for(int i = 0;i<m;i++) {
            int sum = 0;
            for(int j = 0;j<n;j++) {
                if (mat[j][i] != 0 && sum + mat[j][i] > 0) {
                    sum += mat[j][i];
                    map3[j][i] = sum;
                } else if (mat[j][i] == 0) {
                    sum = 0;
                }
            }
            sum = 0;
            for(int j = n-1;j>-1;j--) {
                if (mat[j][i] != 0 && sum + mat[j][i] > 0) {
                    sum += mat[j][i];
                    map4[j][i] = sum;
                } else if (mat[j][i] == 0) {
                    sum = 0;
                }
            }
        }
        System.out.println(Arrays.deepToString(map1));
        System.out.println(Arrays.deepToString(map2));
        System.out.println(Arrays.deepToString(map3));
        System.out.println(Arrays.deepToString(map4));
        for(int i = 0;i<n;i++) {
            for(int j= 0;j<m;j++) {
                if (mat[i][j] == 0) continue;
                int min = 987654321;
                for(int dir = 0;dir<4;dir++) {
                    int ny = i + dy[dir];
                    int nx = j + dx[dir];
                    if (OOB(ny, nx)) continue;
                    if (dir == 0) {
                        min = Math.min(map3[ny][nx] + 1, min);
                    } else if (dir == 1) {
                        min = Math.min(map4[ny][nx] + 1, min);
                    } else if (dir == 2) {
                        min = Math.min(map1[ny][nx] + 1, min);
                    } else {
                        min = Math.min(map2[ny][nx] + 1, min);
                    }
                }
                ans[i][j] = min;
            }
        }
        
        return ans;
    }
    
    static boolean OOB(int y, int x) {
        return y >= n || y < 0 || x >= m || x < 0;
    }
    
}