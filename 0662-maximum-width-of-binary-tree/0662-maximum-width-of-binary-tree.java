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
        int index;

        public Node(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        // 모든 계층 사이에서 가장 최대 넓이를 구하라
        // 넓이 사이에는 null 노드로서 완전이진트리임을 가정하여야 한다.

        // 배열로 표현 가능한 완전 이진트리의 특징으로 왼쪽 자식노드와 오른쪽 자식노드는 언제나 부모노드의 계층 * 2, 부모노드의 계층 * 2 + 1이다.
        // 이는 주의할 것으로 루트노드가 1번부터 시작해야 한다.
        // 그러면 각 계층별로 순회가 가능한 BFS를 사용하여 트리노드 인덱싱을 통한 최대 넓이를 구할 수 있다.
        // 그리고 항상 큐에서 해당 계층내에 마지막 노드와 첫번째 노드 사이가 최대넓이이다.
        // 근데 문제 특성상 오버플로우가 반드시 일어나게 되어있다.
        // 따라서 걍 오버플로우 하게 냅둬도 상관없으니 냅두고 end - start 로 구하면된다.

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(root, 1));
        int ans = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            int start = -1;
            int end = -1;
            for(int i = 0; i < size; i++) {
                Node now = queue.poll();
                if (i == 0) start = now.index;
                if (i == size - 1) end = now.index;
                if (now.node.left != null) queue.add(new Node(now.node.left, now.index * 2));
                if (now.node.right != null) queue.add(new Node(now.node.right, now.index * 2 + 1));
            }
            ans = Math.max(ans, end - start + 1);
            
        }
        return ans;
    }
}