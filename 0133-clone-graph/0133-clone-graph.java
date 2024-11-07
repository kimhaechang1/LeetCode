/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        Node[] nodePool = new Node[101];
        if (node!= null) {
            for(int i = 1;i<nodePool.length;i++) {
                nodePool[i] = new Node(i, new ArrayList<>());
            }
            boolean[] v = new boolean[101];
            v[node.val] = true;
            dfs(node, nodePool, v);
        }
        return nodePool[1];
    }
    static void dfs(Node node, Node[] pool, boolean[] v) {
        
        
        for(Node next: node.neighbors) {
            pool[node.val].neighbors.add(pool[next.val]);
            if (v[next.val]) continue;
            v[next.val] = true;
            dfs(next, pool, v);
        }   
    }
}