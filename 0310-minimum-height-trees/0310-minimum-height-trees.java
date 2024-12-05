class Solution {
    static ArrayList<Integer> []g;
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // 어떠한 두개의 노드도 정확히 하나의 길로 연결되어있는 방향성 없는 그래프인 트리가 있다.
        // 다른말로 하자면 사이클이 없는 모두 연결된 그래프는 트리이다.

        // n개의 노드는 0 ~ n-1의 숫자로 라벨이 붙어있고, n-1[edges] 배열
        // 어떠한 노드로도 루트노드가 될 수 있다.
        // 어떤 노드를 루트로 잡은경우, h의 높이를 가진다.
        // 루트가 있는 모든 가능한 트리들 사이에서 높이의 최솟값이 나올 수 있는 후보 노드를 구하여라
        
        // 높이가 최소가 되게하는 루트를 찾는것, 그럴려면 해당 노드가 최대한 많은 넓이를 가지고 있어야 한다.
        // 그렇다는 것은 리프노드의 개수가 가장 많은 노드가 루트가 될 수 있다.
        // 역으로 생각해서, 가능한한 최대한의 리프노드를 제거하다가, 간선이 1개 또는 0개 남을 때 까지 제거하면 된다.
        // 이를 해결하는 방식이 바로 위상 정렬이다.
        // 위상정렬은 노드들사이에서 어떠한 순서를 지정할 때 사용된다.
        // 그럴때 진입차수 진출차수를 가지고 bfs나 dfs를 돌려서 해결한다.
        int minHeight = 987654321;
        int[] indegree = new int[n];
        g = new ArrayList[n];
        for(int i = 0;i<n;i++) g[i] = new ArrayList<>();
        for(int i = 0;i<n - 1;i++) {
            g[edges[i][0]].add(edges[i][1]);
            g[edges[i][1]].add(edges[i][0]);
            indegree[edges[i][0]]++;
            indegree[edges[i][1]]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        // 리프노드 모두를 bfs를 돌리는 식으로 제거한다

        // 노드가 하나인경우에서는 해당 노드가 루트가 될 수 있다.
        ArrayList<Integer> answer = new ArrayList<>();
        if (edges.length == 0) {
            answer.add(0);
            return answer;
        }
        for(int i = 0;i < n ;i++) {
            if (indegree[i] == 1) queue.add(i);
        }
        System.out.println(Arrays.toString(indegree));

        // 총 간선의 개수는 n - 1개이다.
        int edgeCount = n - 1;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            answer.clear();
            // 간선 개수만큼 빼줌
            edgeCount -= size;
            for(int i = 0;i < size; i++) {
                int node = queue.poll();
                answer.add(node);
                for (int neighbor: g[node]) {
                    indegree[neighbor]--;
                    if (indegree[neighbor] == 1) {
                        queue.add(neighbor);
                    }
                }
            }
        }
        
        return answer;
    }
    
}