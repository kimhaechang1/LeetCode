class Solution {
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        if (n >= 1) {
            dp[1] = 1;
        }
        if (n >= 2) {
            dp[2] = 1;
        }
        int power = 1;
        for(int i = 3;i < n + 1; i++) {
            if (i < (int)Math.pow(2, power + 1)) {
                dp[i] = dp[i - (int)Math.pow(2, power)] + dp[(int)Math.pow(2, power)];
            } else if (i == (int)Math.pow(2, power + 1)) {
                power++;
                dp[(int)Math.pow(2, power)] = 1;
            }
        }
        return dp;
    }
}