class Solution {
    public int[][] merge(int[][] intervals) {
        ArrayList<int[]> lines = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int sIdx = 0;
        int maxPoint = intervals[sIdx][1];
        for(int i = 1;i<intervals.length;i++) {
            if (intervals[i][0] <= maxPoint) {
                maxPoint = Math.max(intervals[i][1], maxPoint);
            } else {
                lines.add(new int[]{intervals[sIdx][0], maxPoint});
                sIdx = i;
                maxPoint = intervals[sIdx][1];
            }
        }

        // 마지막꺼 까먹지말기
        lines.add(new int[]{intervals[sIdx][0], maxPoint});

        int[][] answer = new int[lines.size()][2];
        for(int i = 0;i<answer.length;i++) {
            answer[i] = lines.get(i);
        }
        return answer;
    }
}