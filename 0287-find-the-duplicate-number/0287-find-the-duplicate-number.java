class Solution {
    public int findDuplicate(int[] nums) {
        // 한번이라도 반복되는 숫자가 있다면 그 숫자를 반환하라
        // 이 문제를 품에 있어서 배열 nums를 변형해선 안되고, 오직 상수 추가 공간만 사용해야한다.

        // 이진탐색 법
        // 1 ~ 100_000 원소값이, 100_001 개 주어지는데, 여기서 단 하나의 숫자만이 2번 등장한다.
        // 그러면 어떤 숫자 N 보다 작은 숫자는 N개 나와야하는데 N개 이상이 된다면, 그건 N보다 작은 구간에 반드시 정답이 있다.
        // 결정문제로 풀 수있다.

        int s = 1;
        int e = nums.length - 1; // 배열 내에 가장 큰 숫자
        int ans = e;
        while(s <= e) {
            int mid = (s + e) / 2;
            if (search(mid, nums)) {
                e = mid - 1;
                ans = Math.min(ans, mid);
            } else {
                s = mid + 1;
            }
        }
        return ans;
    }
    static boolean search(int val, int[] nums) {
        int c = 0;
        for(int num: nums) c += (val >= num ? 1 : 0);
        return c > val;
    }
}