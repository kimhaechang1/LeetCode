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

        // 정의하자면, 이진검색트리이기 때문에 각 노드의 키 값이 올 수 있는것이 정해져있다.
        // 그 범위를 결정지을 수 있는 방법은, 만약 왼쪽과 오른쪽이 각각 다르다.
        // 오른쪽 자식노드로 가는경우: 해당 오른쪽 자식의 왼쪽값의 범위는 부모노드가 최소가 되고, 오른쪽 자식노드가 최대가 된다.
        // 왼쪽 자식노드로 가는경우: 왼쪽 자식노드의 오른쪽값의 범위는 부모노드가 최대가 되고, 왼쪽 자식노드가 최소가 된다.
        // 위의 조건범위를 만족하지 못한다면, invalidate 가 된다.
        isBroken = false;
        dfs(root, (long)Integer.MIN_VALUE - 10, root.val, root.val, (long)Integer.MAX_VALUE + 10);
        return !isBroken;
    }
    static void dfs(TreeNode node, long lmin, long lmax, long rmin, long rmax) {
        

        if (node.left != null && (node.left.val <= lmin || node.left.val >= lmax)) {
            isBroken = true;
        }

        //System.out.println("node: "+node.val+" lmin: "+lmin +" lmax: "+lmax+" rmin: "+ rmin+" rmax: "+rmax+" isBroken: "+isBroken);

        if (node.right != null && (node.right.val <= rmin || node.right.val >= rmax)) {
            isBroken = true;
        }

        //System.out.println("node: "+node.val+" lmin: "+lmin +" lmax: "+lmax+" rmin: "+ rmin+" rmax: "+rmax+" isBroken: "+isBroken);

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