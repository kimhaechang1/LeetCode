class Solution {
    public int lengthOfLIS(int[] nums) {
        // 가장 긴 증가부분 수열의 길이를 구하여라
        // 일반적으로 가장 긴 증가부분수열의 길이를 구하는 방식은 N^2이다. 즉, 원소가 약 600개 까지는 커버가된다.
        // 여기서는 원소가 2500이므로, n logn으로 가야한다.

        int[] dp = new int[nums.length + 1];
        dp[0] = nums[0];
        int LIS = 1;
        for(int i = 1; i < nums.length; i++) {
            if (dp[LIS - 1] < nums[i]) {
                dp[LIS++] = nums[i];
            } else {
                int index = find(dp, LIS, nums[i]);
                dp[index] = nums[i];
            }
        }
        return LIS;
    }
    static int find(int[] arr, int length, int f) {
        int s = 0;
        int e = length - 1;
        int ans = e;
        while(s <= e) {
            int mid = (s + e) / 2;
            if (arr[mid] > f) {
                e = mid - 1;
                ans = Math.min(ans, mid);
            } else {
                s = mid + 1;
            }
        }
        return ans;
    }

}