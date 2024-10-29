class Solution {
    public int maxSubArray(int[] nums) {
        int idx = 1;
        int sum = nums[0];
        int ans = sum;
        for(;idx < nums.length;idx++) {
            // 현재 숫자를 더한것 보다 현재 숫자부터 시작하는것이 더 크다면 그걸 선택한다.
            if (sum + nums[idx] < nums[idx]) {
                sum = nums[idx];
            } else {
                sum += nums[idx];
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}