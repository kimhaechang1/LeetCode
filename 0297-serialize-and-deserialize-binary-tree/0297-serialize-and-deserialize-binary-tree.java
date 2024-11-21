/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 import java.util.*;

public class Codec {

    // Encodes a tree to a single string.

    // bfs로 노드를 input대로 넣어주고, bfs대로 빼주면 정답일듯? 
    // 현재 idx에 노드가 있다면, idx + 1, idx + 2는 idx의 자식노드임

    static final String EMPTY = "null";
    static int height = 1;
    public String serialize(TreeNode root) {

        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        height = 1;
        getHeight(root, 1);
        bfs(root, sb);
        
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }
    static void getHeight(TreeNode node, int h) {
        height = Math.max(height, h);
        
        if (node.left != null) {
            getHeight(node.left, h + 1);
        }
        if (node.right != null) {
            getHeight(node.right, h + 1);
        }
    }

    static void bfs(TreeNode node, StringBuilder sb) {

        Queue<TreeNode> queue = new ArrayDeque<>();
        sb.append(node.val).append("|");
        int h = 1;
        queue.add(node);
        while(!queue.isEmpty()) {
            int sz = queue.size();
            for(int i = 0;i<sz;i++) {
                TreeNode nd = queue.poll();
                if (nd.left == null && h < height) {
                    sb.append(EMPTY).append("|");
                } else if (nd.left != null) {
                    sb.append(nd.left.val).append("|");
                    queue.add(nd.left);
                }
                if (nd.right == null && h < height) {
                    sb.append(EMPTY).append("|");
                } else if (nd.right != null) {
                    sb.append(nd.right.val).append("|");
                    queue.add(nd.right);
                }
            }
            ++h;
        }
    }

    public static String[] treefipy(String data) {
        String[] nodes = data.split("\\|");
        String[] rt = new String[nodes.length + 1];
        for(int i = 1;i<rt.length;i++) {
            rt[i] = nodes[i - 1];
        }
        return rt;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("")) return null;
        String[] nodes = treefipy(data);
        TreeNode root = new TreeNode(Integer.parseInt(nodes[1]));
        TreeNode head = root;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int idx = 2;
        while(!queue.isEmpty() && idx < nodes.length) {
            TreeNode node = queue.poll();
            
            if (!nodes[idx].equals(EMPTY)) {
                TreeNode newNode = new TreeNode(Integer.parseInt(nodes[idx]));
                node.left = newNode;
                queue.add(newNode);
            }

            ++idx;

            if (idx < nodes.length && !nodes[idx].equals(EMPTY)) {
                TreeNode newNode = new TreeNode(Integer.parseInt(nodes[idx]));
                node.right = newNode;
                queue.add(newNode);
            }

            ++idx;
        }
        
        return head;   
    }

}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));