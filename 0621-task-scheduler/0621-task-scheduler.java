
class Solution {
    static int[] taskCnt;
    static int[] time;
    public int leastInterval(char[] tasks, int n) {
        // 한가지의 일을 처리할 수 있다.
        // 그리고 동일한 작업에 대해서는 N 간격 이후에 처리가 가능하다.
        
        // 최소가 될려면, 최대한 빡빡하게 작업을 넣어야 한다.
        
        taskCnt = new int[26];
        for(char task: tasks) taskCnt[task - 'A']++;
        
        int answer = 0;
        time = new int[26];
        // 우선순위 큐를 사용해서 작업을 최적화 시켜보자
        // 우선적으로 처리해야하는 작업의 기준은, 많이 남은 작업을 빨리 깎는게 좋다.
        int index = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return b[0] - a[0];
        });
        // 작업 대기자 명단
        System.out.println(Arrays.toString(taskCnt));
        for(int i = 0;i < 26 ;i++) {
            if (taskCnt[i] <= 0) continue;
            pq.add(new int[]{taskCnt[i], 0});
        }

        int time = 0;
        Queue<int[]> tempQueue = new ArrayDeque<>();
        // 작업중인 명단

        while(!pq.isEmpty() || !tempQueue.isEmpty()) {
           
            if (pq.isEmpty()) time = tempQueue.peek()[1];
            // 마지막으로 작업에 들어간 해당 작업의 끝나는 시각이 정답이 된다.

            while(!tempQueue.isEmpty() && tempQueue.peek()[1] <= time) {
                // 작업중 명단에 들어간 task 중 작업이 끝난 task는 다시 작업 대기자 큐에 들어간다.
                pq.add(tempQueue.poll());
            }

            int[] task = pq.poll();
            task[1] = time + n + 1;
            task[0]--;
            
            if (task[0] > 0) tempQueue.add(new int[]{task[0], task[1]});
            // 작업 횟수가 끝난 작업은 작업큐에 들어갈 필요가 없다. (순서상 개수를 구하는 문제이기 때문)
            time++;
            
        }
        return time;
    }
}