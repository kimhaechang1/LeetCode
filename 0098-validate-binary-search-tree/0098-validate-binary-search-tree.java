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
    static boolean isBroken;
    static int rootVal;
    public boolean isValidBST(TreeNode root) {

        // 이진검색트리이기 때문에 각 노드의 키 값이 올 수 있는것이 정해져있다. 
        
        // 그래서 왼쪽과 오른쪽의 노드가 올 수 있는 범위를 정해야 한다.

        // 그 범위를 결정지을 수 있는 방법은, 만약 왼쪽과 오른쪽이 각각 다르다.

        // 현재 노드를 기준으로 왼쪽자식과 오른쪽 자식이 이진트리 특성상 범위에 합당한지 검사하는것이고, 
        
        // 그 범위가 언제 업데이트 되는지를 결정지으면 된다.

        // 또다른 solution으로 이진검색트리를 중위순회한 결과는 언제나 오름차순이라것으로 풀 수 있다.
        
        isBroken = false;
        dfs(root, (long)Integer.MIN_VALUE - 10, root.val, root.val, (long)Integer.MAX_VALUE + 10);
        return !isBroken;
    }
    static void dfs(TreeNode node, long lmin, long lmax, long rmin, long rmax) {
        

        if (node.left != null && (node.left.val <= lmin || node.left.val >= lmax)) {
            isBroken = true;
        }


        if (node.right != null && (node.right.val <= rmin || node.right.val >= rmax)) {
            isBroken = true;
        }

        if (!isBroken && node.left != null) {
            dfs(node.left, lmin, node.left.val, node.left.val, node.val);
        }

        if (!isBroken && node.right != null) {
            dfs(node.right, node.val, node.right.val, node.right.val, rmax);
        }

        if (isBroken) {
            return;
        }
    }
    
}
