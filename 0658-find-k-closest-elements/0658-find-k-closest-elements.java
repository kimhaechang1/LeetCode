class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 정렬된 배열 arr, k 그리고 x가 주어진다. 
        //  x에 가장 가까운 k개의 정수를 반환한다.

        // 더 가깝다의 정의는 둘의 차이가 다른 수와의 차이보다 절댓값으로 더 작거나
        // 같은경우 비교대상 수들의 순서상 더 작은 수

        ArrayList<int[]> dataList = new ArrayList<>();
        List<Integer> answer = new ArrayList<>();
        for(int i = 0; i < arr.length; i++) {
            dataList.add(new int[]{arr[i], Math.abs(arr[i] - x)});
        }
        Collections.sort(dataList, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            } 
            return a[1] - b[1];
        });
        
        for(int i = 0;i < k;i++) {
            answer.add(dataList.get(i)[0]);
        }
        Collections.sort(answer);
        return answer;
    }
}