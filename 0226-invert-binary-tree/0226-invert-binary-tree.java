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
    static int h;
    public TreeNode invertTree(TreeNode root) {
        // 어렵게 생각할 거 없음, 자신의 왼쪽 자식과 오른쪽 자식을 교체하기만 하면됨
        // 연결리스트로 구현된 트리면 부모가 교체되면 그 서브트리도 함께 이동한다
        // 따라서 모든 트리의 높이를 순회하면서 왼쪽 자식과 오른쪽 자식을 교환하면 된다.
        if (root == null) {
            return null;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
    
}