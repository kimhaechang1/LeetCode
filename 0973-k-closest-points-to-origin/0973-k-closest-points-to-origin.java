class Solution {
    public int[][] kClosest(int[][] points, int k) {
        
        Arrays.sort(points, (a, b) -> {
            return ((int)Math.pow(a[0], 2) + (int)Math.pow(a[1], 2)) - ((int)Math.pow(b[0], 2) + (int)Math.pow(b[1], 2));
        });
        
        int[][] answer = new int[k][2];
        for(int i = 0;i<k;i++) {
            answer[i] = points[i];
        }
        return answer;
    }
}