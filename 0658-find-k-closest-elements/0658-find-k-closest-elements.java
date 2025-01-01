class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 정렬된 배열 arr, k 그리고 x가 주어진다. 
        //  x에 가장 가까운 k개의 정수를 반환한다.

        // 더 가깝다의 정의는 둘의 차이가 다른 수와의 차이보다 절댓값으로 더 작거나
        // 같은경우 비교대상 수들의 순서상 더 작은 수

        // 다른 풀이라고 하면 어짜피 정렬되어 있는 숫자들에 대해서 생각해보자.

        // 연속적으로 볼 수 있는 수들이기 때문에 k개의 크기만 맞출 수 있으면 상관없다.
        // 그리고 만약 절댓값 차가 더 크다면 큰쪽을 줄이는건 자명하며,
        // 절댓값 차가 같으면 둘 중 end를 줄이는것이 자명하다.
        int start = 0;
        int end = arr.length - 1;
        while((end - start + 1) > k) {
            int left = Math.abs(arr[start] - x);
            int right = Math.abs(arr[end] - x);
            if (left < right) {
                end--;
            } else if (left > right) {
                start++;
            } else {
                end--;
            }
        }
        List<Integer> answer = new ArrayList<>();
        for(int i = start; i <= end;i++) {
            answer.add(arr[i]);
        }
        return answer;
    }
}