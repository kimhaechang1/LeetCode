class Solution {
    static List<List<Integer>> answer;
    static int n;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        answer = new ArrayList<>();
        Arrays.sort(candidates);
        if (candidates[0] > target) return answer;
        n = target / candidates[0];
        dfs(candidates, target, new ArrayList<>(), 0, 0, 0);
        return answer;
    }
    static void dfs(int[] candis, int target, List<Integer> box, int depth, int idx, int sum) {
        System.out.println(box+" sum: "+sum);
        if (sum > target) return;
        if (depth == n || idx == candis.length || sum == target) {
            if (sum == target) answer.add(new ArrayList<>(box));
            return;
        }

        sum += candis[idx];
        box.add(candis[idx]);
        depth += 1;
        dfs(candis, target, box, depth, idx, sum);
        depth -= 1;
        box.remove(depth);
        sum -= candis[idx];

        

        idx += 1;
        dfs(candis, target, box, depth, idx, sum);
        idx -= 1;
    }
}