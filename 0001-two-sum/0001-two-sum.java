import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[2];
        for(int i = 0;i<nums.length;i++) {
            int cha = target - nums[i];
            if (map.getOrDefault(cha, -1) > -1) {
                ans[0] = map.get(cha);
                ans[1] = i;
                break;
            }
            map.put(nums[i], i);
        }
        return ans;
    }
}