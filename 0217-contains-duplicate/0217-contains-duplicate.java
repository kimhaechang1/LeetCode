class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        // 어떠한 값이라도 적어도 두번이상 나타난다면 true
        int prev = nums[0];
        for(int i = 1;i < nums.length; i++) {
            if (prev == nums[i]) return true;
            prev = nums[i];
        }
        return false;
    }
}