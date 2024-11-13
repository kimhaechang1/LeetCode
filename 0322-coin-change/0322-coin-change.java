import java.util.*;

class Solution {
    static int n;
    static int a;
    static int min;
    static boolean[] v;
    static int[] result;
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for(int i = 1;i<=amount;i++) {
            for(int j = 0;j<coins.length;j++) {
                if (i - coins[j] > 0 && dp[i - coins[j]] > 0) {
                    if (dp[i] == -1) dp[i] = dp[i - coins[j]] + 1;
                    else {
                        dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                    }
                } else if (i - coins[j] == 0) {
                    dp[coins[j]] = 1;
                }
            }
        }
        return dp[amount];
    }
    
}