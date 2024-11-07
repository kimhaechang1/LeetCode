class Solution {
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int n;
    static int m;
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (color == image[sr][sc]) return image;
        n = image.length;
        m = image[0].length;
        
        int origin = image[sr][sc];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{sr, sc});
        image[sr][sc] = color;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            
            for(int dir = 0;dir<4;dir++) {
                int ny = now[0] + dy[dir];
                int nx = now[1] + dx[dir];
                if (OOB(ny, nx) || image[ny][nx] != origin) continue;
                image[ny][nx] = color;
                queue.add(new int[]{ny, nx});
            }
        }
        
        return image;
    }
    static boolean OOB(int y, int x){
        return y >= n || y < 0 || x >= m || x < 0;
    }
}