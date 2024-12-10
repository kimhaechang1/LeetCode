class Solution {
    static int[] dp;
    static int n;
    public int rob(int[] nums) {
        // 거리 사이에 있는 집들을 터는것을 계획하고있는 전문강도다.
        // 각 집은 특정한 양의 숨겨둔 돈을을 갖고있으며, 
        // 제약조건으로, 인접한 두 집을 같은날에 털면 방범장치가 작동한다.
        
        // 돈의 최대값을 찾아내라.
        // dp 아닌가?
        
        // 첫번째 집을 털었으면 무조건 세번째 집을 털어야하고
        // 첫번째 집을 스킵하고 두번째 집을 털었다면, 세번째집을 못털고 네번째 집을 털 수 있다.
        n = nums.length;
        dp = new int[n];
        Arrays.fill(dp, - 1);
        dfs(0, nums);
        if (n > 1) dfs(1, nums);
        System.out.println(Arrays.toString(dp));
        return Math.max(dp[0], (n > 1 ? dp[1] : 0));
    }
    static int dfs(int depth, int[] arr) {

        if (dp[depth] != -1) {
            return dp[depth];
        }
        dp[depth] = arr[depth];
        int max = 0;
        for(int i = depth + 2; i < n ;i++) {
            
            max = Math.max(dfs(i, arr), max);
        }
        dp[depth] += max;
        return dp[depth];
    }
}