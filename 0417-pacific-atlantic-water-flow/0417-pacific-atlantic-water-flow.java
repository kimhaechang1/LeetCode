class Solution {
    static int n, m;
    static int N, M;
    static List<List<Integer>> answer;
    static int[][] map;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // n * m 직사각형의 섬이 있고 태평양과 대서양으로 둘러싸인
        // 섬에 빗물이 들어오면 자신과 같거나 더 작은 방향으로 빗물이 흐르게 된다.
        // 즉, 어떤 셀에서 태평양과 대서양으로 모두 흘러내릴 수있는 좌표들을 리턴하면 됨
        n = heights.length;
        m = heights[0].length;
        answer = new ArrayList<>();
        // pacific 에서 bfs로 한번 다 덮고
        // atlantic 에서 bfs로 한번 다 덮고
        boolean[][] pv = new boolean[n][m];
        boolean[][] av = new boolean[n][m];

        Queue<int[]> queue1 = new ArrayDeque<>();
        Queue<int[]> queue2 = new ArrayDeque<>();
        for(int i = 0; i < m; i++) {
            pv[0][i] = true;
            queue1.add(new int[]{0, i, heights[0][i]});
            av[n - 1][i] = true;
            queue2.add(new int[]{n - 1, i, heights[n - 1][i]});
        }
        for(int i = 0; i < n; i++) {
            pv[i][0] = true;
            queue1.add(new int[]{i, 0, heights[i][0]});
            av[i][m - 1] = true;
            queue2.add(new int[]{i, m - 1, heights[i][m - 1]});
        }
        
        bfs(queue1, pv, heights);
        bfs(queue2, av, heights);

        for(int i = 0; i < n; i++) {
            for(int j = 0;j < m;j ++) {
                if (pv[i][j] && av[i][j]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);list.add(j);
                    answer.add(list);
                }
            }
        }
        return answer;
    }
    static void bfs(Queue<int[]> queue, boolean[][] v, int[][] map){
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            for(int dir = 0; dir < 4; dir++) {
                int ny = now[0] + dy[dir];
                int nx = now[1] + dx[dir];
                if (ny >= n || ny < 0 || nx >= m || nx < 0 || v[ny][nx] || now[2] > map[ny][nx]) continue;
                v[ny][nx] = true;
                queue.add(new int[]{ny, nx, map[ny][nx]});
            }
        }
    }

    static void printMap(int[][] map) {
        for(int i = 0;i < N ;i++) {
            for(int j = 0;j < M; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}