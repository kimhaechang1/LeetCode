class Solution {
    static ArrayList<Integer>[] g;
    static boolean isCycle;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 길이 2인 배열이 5000개 까지 들어올 수 있는 배열 prerequisites
        // pre[1] 이 먼저 수행되어야 pre[0]을 나아갈 수 있음
        // union find를 사용해서 union에 실패하는 경우를 걸러내는 방법

        // union find 로 풀수 없는 이유는, 어떤 노드는 다른 두 노드의 이전노드로 사용될 수 있음
        // 순전히 사이클이 발생하는지 여부를 체크하는게 좋다.
        isCycle = false;
        g = new ArrayList[numCourses];
        for(int i = 0;i<numCourses;i++) {
            g[i] = new ArrayList<>();
        }
        for(int[]pre: prerequisites) {
            int s = pre[1];
            int e = pre[0];

            g[s].add(e);
        }
        int[] v = new int[numCourses];
        // 사이클이 발생하지 않은 것으로 체크가 끝남: -2 
        // 현재 진행하면서 방문했음: -1
        for(int i = 0;i<numCourses;i ++) {
            if (v[i] == -2) continue;
            v[i] = -1;
            dfs(i, v);
            if (isCycle) return false;
        }
        
        return true;
    }
    static void dfs(int node, int[] v) {
        if(isCycle) return;
        for(int next: g[node]) {
            if(v[next] == -1) {
                isCycle = true;
                return;
            } else if (v[next] == 0) {
                v[next] = -1;
                dfs(next, v);
            }
        }
        v[node] = -2;
        dfs2(node, v);
    }
    static void dfs2(int node, int[] v) {

        for(int next: g[node]) {
            if (v[next] == -1) {
                v[next] = -2;
                dfs2(next, v);
            }
        }
    }
}