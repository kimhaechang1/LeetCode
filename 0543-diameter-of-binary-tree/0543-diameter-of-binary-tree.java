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
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;

        // 결국 트리의 지름의 최대값을 구하는것인데, 
        // 어떤 노드의 왼쪽 자식노드 최대높이와 오른쪽 자식노드 최대높이의 합이 지름이된다.
        dfs(root);
        return ans;
    }
    static int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int l = dfs(node.left);
        int r = dfs(node.right);
        ans = Math.max(ans, l + r); // 현재 노드의 왼쪽 높이와 오른쪽 높이의 합
        return Math.max(l, r) + 1; // 타고 온 간선당 높이가 1이므로 되돌아가면서 1을 더해야함
    }
}