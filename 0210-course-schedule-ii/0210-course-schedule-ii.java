class Solution {
    static ArrayList<Integer> []g;
    static int n;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 0 ~ numCourses - 1 번까지 라벨이 붙은 강의실이 있다.
        // a를 들으려면, 반드시 b를 들어야한다.? -> 위상정렬 느낌?
        // [0, 1] 은 0을 듣기위해서 1을 반드시 수강해야 한다는 것

        // 문제를 읽어보니 impossible한 상태가 있음, 즉 사이클이 존재하는 경우가 있다는 뜻

        n = numCourses;
        int[] answer = new int[n];
        int[] indegree = new int[n];
        g = new ArrayList[n];
        for(int i = 0; i < n; i ++) g[i] = new ArrayList<>();
        for(int i = 0; i < prerequisites.length;i ++) {
            g[prerequisites[i][1]].add(prerequisites[i][0]);
            indegree[prerequisites[i][0]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] v= new boolean[n];
        for(int i = 0;i < n; i ++) {
            if (indegree[i] == 0) {
                v[i] = true;
                queue.add(i);
            }
        }
        int index = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0;i < size;i ++) {
                int node = queue.poll();
                answer[index++] = node;
                for(int next: g[node]) {
                    indegree[next]--;
                    if (indegree[next] == 0) {
                        v[next] = true;
                        queue.add(next);
                    }
                }
            }
        }
        // 모든 그래프내의 간선을 정렬하는것이기에, 방문이 안된 노드가 존재한다는 것은 사이클이 존재한다는 것이다.
        return index == n ? answer: new int[]{};
    }
}