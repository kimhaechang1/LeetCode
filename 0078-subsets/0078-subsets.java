class Solution {
    static boolean[] select;
    static int n;
    static List<List<Integer>> answer;
    public List<List<Integer>> subsets(int[] nums) {
        n = nums.length;
        answer = new ArrayList<>();
        select = new boolean[n];
        dfs(nums, 0);
        return answer;
    }

    static void dfs(int[] elems, int depth) {
        if (depth == n) {
            List<Integer> list = new ArrayList<>();
            for(int i = 0;i<select.length;i++) {
                if (!select[i]) continue;
                list.add(elems[i]);
            }
            answer.add(list);
            return;
        }

        select[depth] = true;
        dfs(elems, depth + 1);

        select[depth] = false;
        dfs(elems, depth + 1);
    }
}