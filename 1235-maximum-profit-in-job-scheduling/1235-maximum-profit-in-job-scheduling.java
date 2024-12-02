class Solution {
    static class Job {
        int start;
        int end;
        int profit;

        public Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        // 최고 이윤을 반환하라 , 어떠한 두개작업도 시간 범위가 겹치지 않는
        // 어떤 작업의 끝나는시간이 X이면 X시간에 또다른작업을 바로 시작할 수 있다.

        // 생각해보면 어쨋든 최대한 넣는것이 중요하고
        // 특정끝나는 시각까지의 최대값을 알아야 한다.
        
        Job[] jobs = new Job[startTime.length];

        for(int i = 0;i<jobs.length;i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs, (a, b) -> {  
            return a.end - b.end;
        });

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for(int i = 0;i<jobs.length;i++) {
            Job select = jobs[i];
            Map.Entry<Integer, Integer> floorEntry = treeMap.floorEntry(select.start);
            int profitSum = (floorEntry == null ? 0 : floorEntry.getValue()) + jobs[i].profit;
            
            floorEntry = treeMap.floorEntry(select.end);
            int maxValue = (floorEntry == null ? 0 : floorEntry.getValue());
            
            // 자기 앞의 끝나있는 시각대의 값들 중에서 최대를 골라야 한다.
            // 현재 작업의 시작시간보다 적은 값들 중에서 최대를 골라야 한다.
            // 최적의 값을 꺼낸다 생각하면 이전까지의 최대값을 꺼내서 현재 볼 수 있는 이득과 비교한다.
            // 새롭게 시작하는것 보다 이전까지의 최적값을 선택하는게 좋을 수 있다. 
            // 왜냐하면 어짜피 해당 끝나는 시각까지 얻을 수 있는 최대값을 다른 시작시각에서 또 사용하기 때문이다.
            
            treeMap.put(select.end, Math.max(maxValue, profitSum));
        }
        
        // 마지막까지 끝나는 시각 기준 누적 최대값을 리턴한다.
        return treeMap.lastEntry().getValue();
    }
}