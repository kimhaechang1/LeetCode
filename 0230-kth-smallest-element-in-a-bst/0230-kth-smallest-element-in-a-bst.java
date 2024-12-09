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
    static int answer;
    static int depth;
    static void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        depth--;
        if (depth == 0) {
            answer = node.val;
            return;
        }
        dfs(node.right);
    }
    public int kthSmallest(TreeNode root, int k) {
        // 이진검색트리에서 k번째 가장 작은 value를 뽑아라
        // 이진검색트리의 특징으로 중위순회를 하는경우 순서대로 뽑힌다.
        depth = k;
        dfs(root);
        return answer;
    }
}