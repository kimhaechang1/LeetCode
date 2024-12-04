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

    static HashMap<Integer, Integer> inMap;
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 중요 관찰: inorder와 preorder 사이의 관계
        // inorder는 어떤 노드가 왼쪽 서브트리인지 오른쪽 서브트리인지 찾을 수 있다.
        // preorder는 순서대로 부모노드가 된다.
        // preorder의 노드번호를 기준으로 inorder를 살펴보면 항상 0 ~ inorder[preorder]은 왼쪽 서브트리 개수이고
        // inorder[preorder] ~ inorder[이전 preorder - 1]은 오른쪽 서브트리의 개수가 된다.
        // 따라서 범위를 좁혀나가면서 노드를 추가하는 방식을 채택 -> 분할정복

        inMap = new HashMap<>();
        for(int i = 0;i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        
        TreeNode root = dfs(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        return root;
    }
    static TreeNode dfs(int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {

        if (ps > pe || is > ie) return null;

        TreeNode parent = new TreeNode(preorder[ps]);

        int parentIdx = inMap.get(preorder[ps]);

        // inorder array에서 부모노드의 위치를 구함

        int leftSubtreeCount = parentIdx - is;

        // 이전 부모노드의 기준점에서 부터 새로운 부모노드의 위치사이의 개수가 왼쪽 서브트리의 개수가 됨

        parent.left = dfs(preorder, ps + 1, ps + leftSubtreeCount, inorder, is, parentIdx - 1);

        // preorder: 왼쪽을 먼저 탐색할 것이라면, ps 포인터를 하나 옮기고 한계점은 당연히 왼쪽 서브트리 개수만큼임
        // inorder: 왼쪽 탐색이라면 이전 부모노드의 위치 - 1 까지가 범위라고 볼 수 있음, 
        // 왜냐하면 서브트리노드들만 범위로 잡아야 하기 때문

        parent.right = dfs(preorder, ps + leftSubtreeCount + 1, pe, inorder, parentIdx + 1, ie);

        // preorder: 오른쪽을 탐색할 것이라면, ps 포인터는 왼쪽 개수만큼 뛰어넘어야함
        // inorder: 오른쪽을 탐색할 것이라면 is포인터는 부모 노드의 위치 + 1이어햠

        return parent;
    }
}