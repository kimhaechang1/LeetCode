class Solution {
    public static final int INIT = -1_111_111_111;
    public int majorityElement(int[] nums) {
        // majority element 는 n / 2 번 이상 등장하는 원소를 말한다.
        int n = nums.length;
        int target = n % 2 == 0 ? n / 2 : (n / 2) + 1;
        Arrays.sort(nums);
        int prev = INIT;
        int cnt = 0;
        int ans = 0;
        for(int i = 0; i<n;i++) {
            if (prev == nums[i]) {
                cnt++;
            } else {
                cnt = 1;
                prev = nums[i];
            }
            if (cnt == target) {
                ans = nums[i];
                break;
            }
        }
        return ans;
    }
}