import java.util.*;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 선분 덮어씌우기
        TreeMap<Integer, Integer> pointMap = new TreeMap<>();
        TreeMap<Integer, Integer> lineMap = new TreeMap<>();
        
        for(int[] interval: intervals) {
            pointMap.put(interval[0], pointMap.getOrDefault(interval[0], 0) + 1);
            pointMap.put(interval[1], pointMap.getOrDefault(interval[1], 0) - 1);
        }
        pointMap.put(newInterval[0], pointMap.getOrDefault(newInterval[0], 0) + 1);
        pointMap.put(newInterval[1], pointMap.getOrDefault(newInterval[1], 0) - 1);
        System.out.println(pointMap);
        int sum = 0;
        for(Map.Entry<Integer, Integer> pointMapEntry: pointMap.entrySet()) {
            int point = pointMapEntry.getKey();
            sum += pointMapEntry.getValue();
            lineMap.put(point, lineMap.getOrDefault(point, 0) + sum);
        }

        int s = -1;
        ArrayList<int[]> ansList = new ArrayList<>();
        System.out.print(lineMap);
        for(Map.Entry<Integer, Integer> lineMapEntry: lineMap.entrySet()) {
            int p = lineMapEntry.getKey();
            int v = lineMapEntry.getValue();
            if (s == -1) {
                if (v > 0) {
                    s = p;
                } else if (v == 0) {
                    ansList.add(new int[]{p, p});    
                }
            } else if (s != - 1 && v == 0) {
                ansList.add(new int[]{s, p});
                s = -1;
            }
        }
        int[][] ans = new int[ansList.size()][2];
        for(int i = 0;i<ansList.size();i++) {
            ans[i] = ansList.get(i);
        }
        
        return ans;
    }
}