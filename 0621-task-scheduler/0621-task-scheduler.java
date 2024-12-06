
class Solution {
    static int[] taskCnt;
    static int[] time;
    public int leastInterval(char[] tasks, int n) {
        // 한가지의 일을 처리할 수 있다.
        // 그리고 동일한 작업에 대해서는 N 간격 이후에 처리가 가능하다.
        
        // 최소가 될려면, 최대한 빡빡하게 작업을 넣어야 한다.
        
        // int cnt = 0;
        taskCnt = new int[26];
        for(char task: tasks) taskCnt[task - 'A']++;
        
        int answer = 0;
        time = new int[26];
        // A B C D E A B C D A B C
        int index = 0;
        while(!check()) {
            boolean canGo = false;
            int findIdx = -1;
            int prevIdx = index;
            while(true) {
                if (!canGo && taskCnt[index] > 0 && time[index] == 0) {
                    //System.out.println("select: "+(char)(index + 'A'));
                    findIdx = index;
                    canGo = true;
                    break;
                }
                index++;
                if (index >= 26) {
                    index = 0;
                }
                if (index == prevIdx) {
                    break;
                }
            }
            for(int i = 0; i < 26; i++) {
                if (i != findIdx && time[i] > 0) {
                    time[i]--;
                }
            }

            if (!canGo) {
                //System.out.println("select: idle");
            } else { 
                time[findIdx] = n;
                taskCnt[findIdx]--;
            }
            answer++;
        }
        return answer;
    }
    static boolean check() {
        for(int i = 0;i < 26;i++) {
            if (taskCnt[i] > 0) return false;
        }
        return true;
    }
}