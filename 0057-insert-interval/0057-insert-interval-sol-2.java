import java.util.*;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 라인 스위핑
        ArrayList<int[]> intervalList = new ArrayList<>(Arrays.asList(intervals));
        ArrayList<int[]> ansList = new ArrayList<>();
        intervalList.add(newInterval);
        Collections.sort(intervalList, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] line = intervalList.get(0);
        // 먼저시작하는 좌표에 대해서 처리
        for(int i = 1;i<intervalList.size();i++) {
            int[] now = intervalList.get(i);
            if (line[1] >= now[0]) {
                line[1] = Math.max(line[1], now[1]);
            } else {
                ansList.add(line);
                line = now;
            }
        }
        ansList.add(line); // 마지막 좌표 넣기
        return ansList.toArray(new int[ansList.size()][]);
    }
}
