class Solution {
    static int[] memo;
    static int n;
    public boolean canJump(int[] nums) {
        // 정수배열 num가 주어진다.
        // 너는 배열의 첫번째 인덱스에 위치해있다.
        // 각요소는 그 위치에서 너의 최대 점프 길이 표현하고 잇다.

        // 마지막 인덱스에 도달이 가능하다면 true를, 불가능하다면 false를 반환하라

        memo = new int[nums.length];
        n = nums.length;
        // 0: 가능, 1: 불가능, -1: 도달한 적 없음
        Arrays.fill(memo, -1);
        if (nums.length == 1) return true;

        int[] dp = new int[nums.length];
        boolean[] isReached = new boolean[nums.length];
        isReached[0] = true;
        for(int i = 1; i < nums.length; i++) {
            boolean flag = false;
            for(int j = i - 1; j > -1; j--) {
                if (i - j <= nums[j]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) return false;
        }
        
        return true;
    }
    static int dfs(int depth, int[] nums) {
        if (memo[0] == 0) return 0;
        if (depth == n - 1) {
            return 0;
        }
        if (memo[depth] != -1) {
            return memo[depth];
        }

        memo[depth] = 1;
        boolean isFind = false;
        for(int step = 1; step <= nums[depth]; step++) {
            if (depth + step < n && memo[depth + step] != 1) {
                isFind = true;
                memo[depth] = dfs(depth + step, nums);
            }
        }
        if (!isFind) memo[depth] = 1;

        return memo[depth];
    }
}