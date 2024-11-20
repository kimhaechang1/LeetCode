class Solution {
    static List<List<Integer>> answer;
    public List<List<Integer>> permute(int[] nums) {
        boolean[] v = new boolean[nums.length];
        answer = new ArrayList<>();
        dfs(new ArrayList<>(), nums, v);
        return answer;
    }
    static void dfs(List<Integer> box, int[] nums, boolean[] v) {
        if (box.size() == nums.length) {
            answer.add(new ArrayList<>(box));
            return;
        }

        for(int i = 0;i<nums.length;i++) {
            if (v[i]) continue;
            v[i] = true;
            box.add(nums[i]);
            dfs(box, nums, v);
            box.remove(box.size() - 1);
            v[i] = false;
        }
    }
}