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
    static int ans;
    static int min;
    public int maxPathSum(TreeNode root) {
        // 최대 경로의 합
        ans = -1000 * 30000 - 24;
        min = ans;
        // 트리의 특징 때문에 반드시 자신의 또다른 자식노드 혹은 서브트리를 탈려면, 반드시 부모노드를 거쳐야한다.
        // 문제 잘못이해함
        // 쭉 이어갈 수 있는 경로의 합이어야 한다.
        // 왼쪽과 오른쪽 중 하나만을 선택해야만 하며, 어떠한 서브트리에서 정답이 결장되는 경우라면 왼쪽 오른쪽의 합을 모두 루트와 더한경우가 된다.
        dfs(root);
        return ans;
    }
    static int dfs(TreeNode node) {
        if (node == null) {
            return min;
        }

        int leftMax = dfs(node.left);
        int rightMax = dfs(node.right);

        ans = Math.max(node.val, Math.max(ans, Math.max(leftMax + node.val, Math.max(rightMax + node.val, leftMax + rightMax + node.val))));
        
        return Math.max(node.val, Math.max(leftMax, rightMax) + node.val);
    }
}