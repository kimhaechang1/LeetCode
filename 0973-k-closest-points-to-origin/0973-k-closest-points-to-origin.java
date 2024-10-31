class Solution {
    public int[][] kClosest(int[][] points, int k) {
        ArrayList<int[]> ansList = new ArrayList<>();
        int[] values = new int[points.length];
        ArrayList<Integer> valueList = new ArrayList<>();
        for(int i = 0;i<points.length;i++) {
            valueList.add(i);
            values[i] = (int)Math.pow(points[i][0], 2) + (int)Math.pow(points[i][1], 2);
        }
        
        Collections.sort(valueList, (a, b) -> {
            return values[a] - values[b];
        });
        
        System.out.println(valueList);
        for(int i = 0;i<k;i++) {
            ansList.add(points[valueList.get(i)]);
        }
        return ansList.toArray(new int[ansList.size()][]);
    }
}