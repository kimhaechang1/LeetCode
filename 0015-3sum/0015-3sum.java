class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        // 순서는 중요하지 않음
        HashSet<List<Integer>> result = new HashSet<>();
        Arrays.sort(nums);
        for(int i = 0;i<nums.length;i++) {
            int s = i + 1;
            int e = nums.length - 1;
            while(s < e) {
                int sum = nums[i] + nums[s] + nums[e];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[s], nums[e]));
                    s++;
                    e--;
                    
                } else if (sum < 0) {
                    s++;
                } else {
                    e--;
                }
            }
        }
        
        answer.addAll(result);
        return answer;
    }
}