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
    static class Node {
        TreeNode node;
        int idx;

        public Node(TreeNode node, int idx) {
            this.node = node;
            this.idx = idx;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        // 모든 레벨에서 넓이중 최대를 최대치 넓이라고 가정한다.
        // 한계층의 넒이는 그 계층내에 노드들의 좌우 폭이다. 포화이진트리임을 가정하기에 없는 부분은 null 이 된다.
        int ans = 1;
        // 포화 이진트리이기 때문에, 각 노드의 인덱스를 충분히 계산할 수 있다.
        // 어떤 노드의 왼쪽 자식노드는 2* 노드번호, 오른쪽 자식노드는 2*노드번호 + 1이다.
        // 노드정보와 인덱스번호를 같이 적어놓는 클래스를 만들어서 BFS 돌리면 될듯
        Queue<Node> queue = new ArrayDeque<>();       
        queue.add(new Node(root, 1));
        while(!queue.isEmpty()) {
            int size = queue.size();
            int start = -1;
            int end = -1;
            for(int i = 0;i < size;i ++) {
                Node now = queue.poll();
                if (i == 0) {
                    start = now.idx;
                }
                if (i == size - 1) {
                    end = now.idx;
                }

                if (now.node.left != null) {
                    queue.add(new Node(now.node.left, 2 * now.idx));
                } 
                if (now.node.right != null) {
                    queue.add(new Node(now.node.right, 2 * now.idx + 1));
                }
            }
            ans = Math.max(ans, end - start + 1);
        }
        return ans;
    }
}