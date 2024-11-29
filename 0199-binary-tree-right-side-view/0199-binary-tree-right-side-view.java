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
    static List<Integer> answer;
    public List<Integer> rightSideView(TreeNode root) {
        // 오른쪽에서 쳐다봤을 때 보이는 노드들을 담되, 루트에서부터 보이는 노드를 담아라
        // 각 트리노드의 높이에서, 가장 오른쪽 노드가 정답일거임
        // 노드의 개수가 0인경우가 있으니까 전처리해야함
        answer = new ArrayList<>();
        if (root == null) return answer;
        bfs(root);
        return answer;
    }

    static void bfs(TreeNode node) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(node);
        while(!queue.isEmpty()) {
            int size = queue.size();
            answer.add(queue.peek().val);
            for(int i = 0;i<size;i++) {
                TreeNode now = queue.poll();
                if (now.right != null) {
                    queue.add(now.right);
                } 
                if (now.left != null) {
                    queue.add(now.left);
                } 
            }
        }
    }
}