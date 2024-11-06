class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        HashSet<List<Integer>> result = new HashSet<>();
        Arrays.sort(nums);
        for(int i = 2;i<nums.length;i++) {
            int gap = 0 - nums[i];
            HashMap<Integer, Integer> map = new HashMap<>();
            for(int j = 0;j<i;j++) {
                int gap2 = gap - nums[j];
                if (map.containsKey(gap2)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[j]); list.add(nums[i]); list.add(gap2);
                    result.add(list);
                }
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            }
        }
        answer.addAll(result);
        return answer;
    }
}