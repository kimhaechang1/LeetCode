import java.util.*;

class Solution {
    static int empty = -1_111_111_111;
    static ArrayList<Integer> bst;
    static HashMap<Integer, Integer> parent;
    static HashMap<Integer, Boolean> v;
    static HashMap<Integer, TreeNode> nodeMap;
    static boolean find;
    static int target;
    static int result;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 두 노드가 어떤 루트노드의 서브트리 집합에 속하는지 검사해야 한다.
        // 어떤 노드에서 자신을 포함해서 부모노드 집합을 만들고
        // 해당 노드를 중심으로 아래 노드들을 완전탐색 했을 때, 또다른 노드가 발견되면 해당 노드가 LCA가 되지않을까?
        // 어느 노드든 상관없을 것 같에
        
        // P를 기준으로 부모노드 집합을 만들고 
        // 해당 노드를 기준으로 아래의 모든 서브트리 노드를 순회한다.
        // 이진트리이므로 자신의 부모는 /2로 알 수 있다.
        bst = new ArrayList<>();
        parent = new HashMap<>();
        v = new HashMap<>();
        nodeMap = new HashMap<>();
        bfs(root);
        target = q.val;
        int[] bstNodes = treepify();
        //System.out.println(Arrays.toString(bstNodes));
        int start = p.val;
        find = false;
        while(true) {
            result = start;
            dfs(nodeMap.get(start));
            if (find) return nodeMap.get(result);
            if (!parent.containsKey(start)) break;
            start = parent.get(start);
        }
        return null;
    }
    static void dfs(TreeNode node) {
        if (find) return;
        if (node.val == target){
            find = true;
            return;
        }
        
        if (node.left != null && !v.getOrDefault(node.left.val, false)) {
            v.put(node.left.val, true);
            dfs(node.left);
        }
        if (node.right != null && !v.getOrDefault(node.right.val, false)) {
            v.put(node.right.val, true);
            dfs(node.right);
        }

        if (find) { 
            return;
        }
    }
    static int[] treepify() {
        int[] nodes = new int[bst.size() + 1];
        for(int i = 0;i<bst.size();i++) {
            nodes[i + 1] = bst.get(i);
        }
        return nodes;
    }
    static void bfs(TreeNode root) {
        Queue<TreeNode> queue =new ArrayDeque<>();
        nodeMap.put(root.val, root);
        bst.add(root.val);
        queue.add(root);
        while(!queue.isEmpty()) {
            int sz = queue.size();
            for(int i = 0;i<sz;i++) {
                TreeNode now = queue.poll();
                if (now.left != null) {
                    nodeMap.put(now.left.val, now.left);
                    parent.put(now.left.val, now.val);
                    bst.add(now.left.val);
                    queue.add(now.left);
                } else {
                    bst.add(empty);
                }
                if (now.right != null) {
                    nodeMap.put(now.right.val, now.right);
                    parent.put(now.right.val, now.val);
                    bst.add(now.right.val);
                    queue.add(now.right);
                } else {
                    bst.add(empty);
                }
            }
        }
    }
}