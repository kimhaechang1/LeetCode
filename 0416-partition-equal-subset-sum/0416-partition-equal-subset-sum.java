class Solution {
    public boolean canPartition(int[] nums) {
        
        // 1 1 2 2
        // knapsack?
        
        int S = 0;
        for(int i = 0;i<nums.length;i++) S += nums[i];
        if (S % 2 != 0) return false;

        int half = S / 2;
        boolean[][] dp = new boolean[nums.length + 1][half + 1];
        // i: i번째의 숫자를 사용해서 j값을 만들수 있는지 여부
        for (int i = 0;i<nums.length;i++) {
            // 0은 언제든지 만들 수 있다.
            dp[i][0] = true;
        }
        
        for(int i = 1;i<nums.length + 1;i++) {
            for(int j = 1;j<=half;j++) {
                // i 개의 원소를 사용해서 도달가능한 숫자들 체크
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[nums.length - 1][half];
    }
}