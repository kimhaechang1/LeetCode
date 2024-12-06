
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
        System.out.println(Arrays.toString(taskCnt));
        for(int i = 0;i < 26 ;i++) {
            if (taskCnt[i] <= 0) continue;
            pq.add(new int[]{taskCnt[i], 0});
        }

        int time = 0;
        Queue<int[]> tempQueue = new ArrayDeque<>();
        // 작업에 들어간 시각을 기준으로 pq에 넣는다.

        while(!pq.isEmpty() || !tempQueue.isEmpty()) {
           
            if (pq.isEmpty()) time = tempQueue.peek()[1] + n + 1;

            while(!tempQueue.isEmpty() && tempQueue.peek()[1] + n < time) {
                pq.add(tempQueue.poll());
            }

            int[] task = pq.poll();
            task[1] = time++;
            task[0]--;
            
            if (task[0] > 0) tempQueue.add(new int[]{task[0], task[1]});
            
        }
        return time;
    }
    // A B A C A G A D
    static boolean check() {
        for(int i = 0;i < 26;i++) {
            if (taskCnt[i] > 0) return false;
        }
        return true;
    }
}