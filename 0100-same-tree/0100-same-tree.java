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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 같은지 다른지 체크해보라
        Queue<TreeNode> pQueue = new ArrayDeque<>();
        Queue<TreeNode> qQueue = new ArrayDeque<>();
        if (p == null && q == null) return true;
        if (p == null && q != null) return false;
        if (p != null && q == null) return false;
        if (p.val != q.val) return false;
        pQueue.add(p);
        qQueue.add(q);
        while(!pQueue.isEmpty() || !qQueue.isEmpty()) {
            if (pQueue.size() != qQueue.size()) {
                return false;
            }
            int size = pQueue.size();
            for(int i = 0; i < size; i ++) {
                TreeNode pnow = pQueue.poll();
                TreeNode qnow = qQueue.poll();
                if (isSame(pnow.left, qnow.left) && isSame(pnow.right, qnow.right)) {
                    if (pnow.left != null) {
                        pQueue.add(pnow.left);
                    }
                    if (qnow.left != null) {
                        qQueue.add(qnow.left);
                    }
                    if (pnow.right != null) {
                        pQueue.add(pnow.right);
                    }
                    if (qnow.right != null) {
                        qQueue.add(qnow.right);
                    }
                } else {
                    return false;
                }
                
                
            }
        }
        return true;
    }
    static boolean isSame(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) return true;
        if (n1 == null && n2 != null) return false;
        if (n1 != null && n2 == null) return false;
        return n1.val == n2.val;
    }
}