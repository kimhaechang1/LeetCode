/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    static HashMap<TreeNode, TreeNode> parent;
    static HashMap<TreeNode, Boolean> v;
    static boolean isFind;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // p ~ p.parent 노드를 기준으로 하는 서브트리에 반대쪽 노드가 포함되어 있다면 그때의 공통조상은 p ~ p.parent 이다.
        // p혹은 q를 bfs를 통해 부모노드 HashMap을 만들고, 
        // 부모노드 맵을 만든 기준노드에서 부모노드로 타고 올라가면서, dfs를 통해 모든 서브트리에 반대쪽 노드가 있는지 검사한다.
        parent = new HashMap<>();
        make(root);
        TreeNode start = p;
        v = new HashMap<>();
        TreeNode answer = null;
        isFind = false;
        while(start != null) {
            dfs(start, q);
            if (isFind) {
                answer = start;
                break;
            }
            start = parent.getOrDefault(start, null);
        }
        return answer;
    }
    static void make(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode now = queue.poll();
            if (now.left != null) {
                parent.put(now.left, now);
                queue.add(now.left);
            }
            if (now.right != null) {
                parent.put(now.right, now);
                queue.add(now.right);
            }
        }
    }
    static void dfs(TreeNode node, TreeNode find) {
        if (node == null) {
            return;
        }

        if (find.val == node.val) {
            isFind = true;
            return;
        }

        if (!isFind && node.left != null && !v.getOrDefault(node.left, false)) {
            v.put(node.left, true);
            dfs(node.left, find);
        }

        if (!isFind && node.right != null && !v.getOrDefault(node.right, false)) {
            v.put(node.right, true);
            dfs(node.right, find);
        }
    }
}