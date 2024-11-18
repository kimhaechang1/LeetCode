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

    public boolean isBalanced(TreeNode root) {
        // 결국 트리의 높이차가 1보다 더 많이 발생한다면, 무조건 불균형 트리가 된다.
        if (root == null) {
            return true;
        }    

        return dfs(root) != -1;
    }
    static int dfs(TreeNode node) {
        if (node.left == null && node.right == null) {
            return 1;
        }

        int lh = 0;
        if (node.left != null) {
            lh = dfs(node.left);
        }

        int rh = 0;
        if (node.right != null) {
            rh = dfs(node.right);
        }

        if (rh == -1 || lh == -1) return -1;

        // 만약 불균형 트리가 아니라면 둘중 더 깊이 들어갔던 값이 존재하는지 체크
        if (Math.abs(rh - lh) <= 1) return Math.max(rh, lh) + 1;
        return -1;
    }
}