class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // 어떠한 값이라도 적어도 두번이상 나타난다면 true
        for(int i = 0;i<nums.length;i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) +  1);
        }

        for(int key: map.keySet()) {
            if (map.get(key) >= 2) return true;
        }
        
        return false;
    }
}