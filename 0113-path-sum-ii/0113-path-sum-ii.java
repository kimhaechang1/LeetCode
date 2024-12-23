/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    static List<List<Integer>> answer;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        // 루트에서 리프노드로 이어지는 길 중에 노드 값의 합이 targetSum과 같은 경로
        answer = new ArrayList<>();
        if (root == null) return answer;
        dfs(root, targetSum, new ArrayList<>(), 0);
        return answer;
    }
    static void dfs(TreeNode node, int sum, List<Integer> paths, int depth) {
        
        paths.add(node.val);
        if (node.left == null && node.right == null) {
            int s = 0;
            for(int val: paths) {
                s += val;
            }
            if (s == sum) {
                List<Integer> added = new ArrayList<>();
                added.addAll(paths);
                answer.add(added);
            }
            return;
        }

        
        
        if (node.left != null) {
            dfs(node.left, sum, paths, depth + 1);
            paths.remove(depth + 1);
        }
        if (node.right != null) {
            dfs(node.right, sum, paths, depth + 1);
            paths.remove(depth + 1);
        }
        
        
    }
}